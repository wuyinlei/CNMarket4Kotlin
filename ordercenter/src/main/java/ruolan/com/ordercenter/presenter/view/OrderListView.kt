package ruolan.com.ordercenter.presenter.view

import ruolan.com.baselibrary.presenter.view.BaseView
import ruolan.com.ordercenter.data.protocol.Order

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
interface OrderListView : BaseView {

    //获取订单列表回调
    fun onGetOrderListResult(result:MutableList<Order>)

    //确认订单回调
    fun onConfirmOrderResult(result:Boolean)

    //取消订单回调
    fun onCancelOrderResult(result:Boolean)

}