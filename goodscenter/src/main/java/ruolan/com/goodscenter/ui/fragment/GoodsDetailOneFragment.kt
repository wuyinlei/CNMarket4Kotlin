package ruolan.com.goodscenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.kotlin.base.utils.YuanFenConverter
import kotlinx.android.synthetic.main.fragment_goods_detail_one.*
import ruolan.com.baselibrary.ui.fragment.BaseMvpFragment
import ruolan.com.goodscenter.R
import ruolan.com.goodscenter.common.GoodsConstant
import ruolan.com.goodscenter.data.protocol.Goods
import ruolan.com.goodscenter.injection.component.DaggerCoodsComponent
import ruolan.com.goodscenter.injection.module.GoodsModule
import ruolan.com.goodscenter.presenter.GoodsDetailPresenter
import ruolan.com.goodscenter.presenter.view.GoodsDetailView

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class GoodsDetailOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {


    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    private var mCurGoods:Goods? = null

    override fun onAddCartResult(result: Int) {


    }


    override fun onGetGoodsDetailResult(result: Goods) {
        mCurGoods = result

        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku


        loadPopData(result)
    }

    private fun loadPopData(result: Goods) {


    }


    override fun injectComponent() {
        DaggerCoodsComponent.builder()
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


    private fun initData() {

        mPresenter.getGoodsDetailList(activity.intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID,-1))

    }

    private fun initView() {


    }


}