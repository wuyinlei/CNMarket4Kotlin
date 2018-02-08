package ruolan.com.ordercenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.toast
import ruolan.com.baselibrary.ext.startLoading
import ruolan.com.baselibrary.ui.fragment.BaseMvpFragment
import ruolan.com.ordercenter.R
import ruolan.com.ordercenter.common.OrderConstant
import ruolan.com.ordercenter.data.protocol.Order
import ruolan.com.ordercenter.injection.component.DaggerOrderComponent
import ruolan.com.ordercenter.injection.module.OrderModule
import ruolan.com.ordercenter.presenter.OrderListPresenter
import ruolan.com.ordercenter.presenter.view.OrderListView
import ruolan.com.ordercenter.ui.adapter.OrderAdapter

/**
 * Created by wuyinlei on 2018/2/8.
 *
 * @function
 */
class OrderFragment : BaseMvpFragment<OrderListPresenter>(),OrderListView {


    lateinit var mAdapter: OrderAdapter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_order,container,false)
    }

    override fun onStart() {
        super.onStart()
        loadData()
        toast("请求数据了")
    }

    override fun onGetOrderListResult(result: MutableList<Order>) {
        if (!result.isEmpty()){
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            mAdapter.setData(result)
        } else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onConfirmOrderResult(result: Boolean) {

    }

    override fun onCancelOrderResult(result: Boolean) {

    }

    override fun injectComponent() {
        DaggerOrderComponent.builder()
                .activityComponent(mActivityComponent)
                .orderModule(OrderModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    /*
       加载数据
    */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getOrderList(arguments.getInt(OrderConstant.KEY_ORDER_STATUS,-1))
    }


    private fun initView() {
        mOrderRv.layoutManager = LinearLayoutManager(activity)
        mAdapter = OrderAdapter(activity)
        mOrderRv.adapter = mAdapter
    }
}