package ruolan.com.ordercenter.presenter

import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.ordercenter.data.protocol.Order
import ruolan.com.ordercenter.presenter.view.OrderListView
import ruolan.com.ordercenter.service.OrderService
import ruolan.com.ordercenter.service.ShipAddressService
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {

    @Inject
    lateinit var orderService: OrderService

    fun getOrderList(orderState: Int) {
        if (!checkNetWork())
            return

        orderService.getOrderList(orderState)
                .excute(object : BaseSubscriber<MutableList<Order>>(mView) {
                    override fun onNext(t: MutableList<Order>) {
                        mView.onGetOrderListResult(t)
                    }
                }, lifecycleProvider)
    }

}