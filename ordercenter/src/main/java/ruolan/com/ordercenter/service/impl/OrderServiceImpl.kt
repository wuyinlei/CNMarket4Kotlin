package ruolan.com.ordercenter.service.impl

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.convert
import ruolan.com.baselibrary.ext.convertBoolean
import ruolan.com.ordercenter.data.protocol.Order
import ruolan.com.ordercenter.data.repository.OrderRepository
import ruolan.com.ordercenter.service.OrderService
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
class OrderServiceImpl @Inject constructor() : OrderService {

    override fun getOrderList(orderState: Int): Observable<MutableList<Order>> {
        return repository.getOrderListByState(orderState).convert()
    }

    override fun getOrderById(orderId: Int): Observable<BaseResp<Order>> {
        return repository.getOrderById(orderId)
    }

    override fun submitOrder(order: Order): Observable<Boolean> {
        return repository.submitOrder(order.id).convertBoolean()
    }


    @Inject
    lateinit var repository: OrderRepository


}