package ruolan.com.ordercenter.presenter

import ruolan.com.baselibrary.presenter.BasePresenter
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

}