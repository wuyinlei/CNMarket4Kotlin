package ruolan.com.ordercenter.presenter

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.ordercenter.data.protocol.Order
import ruolan.com.ordercenter.presenter.view.OrderConfirmView
import ruolan.com.ordercenter.service.OrderService
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

    @Inject
    lateinit var orderService: OrderService

    fun getOrderById(orderId: Int) {
        if (!checkNetWork())
            return

        orderService.getOrderById(orderId)
                .excute(object : BaseSubscriber<BaseResp<Order>>(mView) {
                    override fun onNext(t: BaseResp<Order>) {
                        mView.onOrderResult(t.data)
                    }
                }, lifecycleProvider)
    }

}