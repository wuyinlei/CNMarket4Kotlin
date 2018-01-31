package ruolan.com.goodscenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.kennyc.view.MultiStateView
import com.ruolan.factory.router.RouterPath
import kotlinx.android.synthetic.main.fragment_cart.*
import ruolan.com.baselibrary.ext.setVisible
import ruolan.com.baselibrary.ui.fragment.BaseMvpFragment
import ruolan.com.goodscenter.R
import ruolan.com.goodscenter.data.protocol.CartGoods
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
class CartFragment : BaseMvpFragment<CartPresenter>(), CartView {

    private lateinit var mCartGoodsAdapter :CartGoodsAdapter


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


    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(activity)


    }

    private fun initData() {
        mMultiStateView.viewState = MultiStateView.VIEW_STATE_LOADING
        mPresenter.getCartList()

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
        } else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

    }

    override fun onDeleteCartListResult(result: Boolean) {

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