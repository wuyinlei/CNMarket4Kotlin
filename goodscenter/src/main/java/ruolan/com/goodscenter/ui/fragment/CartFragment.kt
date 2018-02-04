package ruolan.com.goodscenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.kotlin.base.utils.YuanFenConverter
import com.ruolan.factory.router.RouterPath
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ext.setVisible
import ruolan.com.baselibrary.ui.fragment.BaseMvpFragment
import ruolan.com.goodscenter.R
import ruolan.com.goodscenter.data.protocol.CartGoods
import ruolan.com.goodscenter.event.CartAllCheckedEvent
import ruolan.com.goodscenter.event.UpdateTotalPriceEvent
import ruolan.com.goodscenter.injection.component.DaggerCartComponent
import ruolan.com.goodscenter.injection.module.CartModule
import ruolan.com.goodscenter.presenter.CartPresenter
import ruolan.com.goodscenter.presenter.view.CartView
import ruolan.com.goodscenter.ui.adapter.CartGoodsAdapter


@Suppress("DEPRECATION")
/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class CartFragment : BaseMvpFragment<CartPresenter>(), CartView, View.OnClickListener {


    private lateinit var mCartGoodsAdapter: CartGoodsAdapter
    private var mTotalPrice: Long = 0


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservable()
    }

    private fun initObservable() {

        Bus.observe<CartAllCheckedEvent>().subscribe { t: CartAllCheckedEvent ->
            run {
                mAllCheckedCb.isChecked = t.isAllChecked
                updateTotalPrice()
            }
        }
                .registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>().subscribe {
            updateTotalPrice()
        }
                .registerInBus(this)
    }

    /*
      刷新是否为编辑状态
   */
    private fun refreshEditStatus() {
        val isEditStatus = getString(R.string.common_edit) == mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEditStatus.not())
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)

        mHeaderBar.getRightView().text = if (isEditStatus) getString(R.string.common_complete) else getString(R.string.common_edit)


    }

    /*
      更新总价
   */
    private fun updateTotalPrice() {
        mTotalPrice = mCartGoodsAdapter.dataList
                .filter { it.isSelected }
                .map { it.goodsCount * it.goodsPrice }
                .sum()

        mTotalPriceTv.text = "合计:${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(activity)
        mAllCheckedCb.onClick(this)
        mHeaderBar.getRightView().onClick {
            refreshEditStatus()
        }

        //删除按钮事件
        mDeleteBtn.onClick {
            val cartIdList: MutableList<Int> = arrayListOf()
            mCartGoodsAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartIdList) { it.id }
            if (cartIdList.size == 0) {
                toast("请选择需要删除的数据")
            }else {
                mPresenter.delCartList(cartIdList)
            }
        }

        //结算按钮事件
        mSettleAccountsBtn.onClick {
            val cartGoodsList: MutableList<CartGoods> = arrayListOf()
            mCartGoodsAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartGoodsList){it}
            if (cartGoodsList.size == 0) {
                toast("请选择需要提交的数据")
            }else {
                mPresenter.submitCartList(cartGoodsList,mTotalPrice)
            }
        }

    }

    private fun initData() {
        mMultiStateView.viewState = MultiStateView.VIEW_STATE_LOADING
        mPresenter.getCartList()

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.mAllCheckedCb -> {
                if (mAllCheckedCb.isChecked) {
                    for (cart in mCartGoodsAdapter.dataList) {
                        cart.isSelected = true
                    }
                    mCartGoodsAdapter.notifyDataSetChanged()
                } else{
                    for (cart in mCartGoodsAdapter.dataList) {
                        cart.isSelected = false
                    }
                    mCartGoodsAdapter.notifyDataSetChanged()
                }
            }
        }

    }

    /*
       设置Back是否可见
    */
    fun setBackVisible(isVisible: Boolean) {
        mHeaderBar.getLeftView().setVisible(isVisible)
    }


    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        mCartGoodsAdapter = CartGoodsAdapter(activity)
        if (result != null) {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            mCartGoodsAdapter.setData(result)
            mCartGoodsRv.adapter = mCartGoodsAdapter
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

    }

    override fun onDeleteCartListResult(result: Boolean) {
        refreshEditStatus()
    }

    override fun onSubmitCartListResult(result: Int) {

    }

    override fun injectComponent() {
        DaggerCartComponent.builder()
                .activityComponent(mActivityComponent)
                .cartModule(CartModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun onNoPermission() {
        mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR
        mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR)?.setOnClickListener({
            //跳转到登录界面
            ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
        })
    }


}