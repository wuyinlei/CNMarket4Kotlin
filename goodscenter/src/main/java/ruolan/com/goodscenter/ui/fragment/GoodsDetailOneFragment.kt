package ruolan.com.goodscenter.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.utils.YuanFenConverter
import kotlinx.android.synthetic.main.fragment_goods_detail_one.*
import org.jetbrains.anko.contentView
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ui.activity.BaseActivity
import ruolan.com.baselibrary.ui.fragment.BaseMvpFragment
import ruolan.com.baselibrary.widget.BannerImageLoader
import ruolan.com.goodscenter.R
import ruolan.com.goodscenter.common.GoodsConstant
import ruolan.com.goodscenter.data.protocol.Goods
import ruolan.com.goodscenter.event.AddCartEvent
import ruolan.com.goodscenter.event.SkuChangedEvent
import ruolan.com.goodscenter.event.UpdateCartSizeEvent
import ruolan.com.goodscenter.injection.component.DaggerGoodsComponent
import ruolan.com.goodscenter.injection.module.GoodsModule
import ruolan.com.goodscenter.presenter.GoodsDetailPresenter
import ruolan.com.goodscenter.presenter.view.GoodsDetailView
import ruolan.com.goodscenter.ui.activity.GoodsDetailActivity
import ruolan.com.goodscenter.widget.GoodsSkuPopView

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class GoodsDetailOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    private lateinit var mSkuPop: GoodsSkuPopView

    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    private var mCurGoods: Goods? = null

    override fun onAddCartResult(result: Int) {
        Bus.send(UpdateCartSizeEvent())
    }


    /**
     * 请求接口获取到数据的回调函数
     */
    override fun onGetGoodsDetailResult(result: Goods) {
        mCurGoods = result

        val split = result.goodsBanner.split(",")
        //用于显示数据
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setImages(split)
        mGoodsDetailBanner.setDelayTime(3000)
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku


        loadPopData(result)
    }

    /**
     * 加载弹框   用于显示选择各种搭配
     */
    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)

    }


    override fun injectComponent() {
        DaggerGoodsComponent.builder()
                .activityComponent(mActivityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_goods_detail_one, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAnim()
        initView()
        initData()
        initSkuPop()
        initObserve()
    }

    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    /*
        初始化sku弹层
     */
    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity)
        mSkuPop.setOnDismissListener {
            (activity as BaseActivity).contentView?.startAnimation(mAnimationEnd)
        }
    }

    /*
      监听SKU变化及加入购物车事件
   */
    @SuppressLint("SetTextI18n")
    private fun initObserve() {
        Bus.observe<SkuChangedEvent>()
                .subscribe {
                    mSkuSelectedTv.text = mSkuPop.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() + "件"
                }.registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }.registerInBus(this)
    }

    private fun addCart() {

        mCurGoods?.let {
            mPresenter.addCart(it.id,
                    it.goodsDesc,
                    it.goodsDefaultIcon,
                    it.goodsDefaultPrice,
                    mSkuPop.getSelectCount(),
                    mSkuPop.getSelectSku()
            )
        }
    }


    private fun initData() {

        mPresenter.getGoodsDetailList(activity.intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, -1))

    }

    private fun initView() {

        //sku弹层
        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as GoodsDetailActivity).contentView
                    , Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
                    0, 0
            )
            (activity as BaseActivity).contentView?.startAnimation(mAnimationStart)
        }

    }


}