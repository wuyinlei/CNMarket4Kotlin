package ruolan.com.ordercenter.service.impl

import ruolan.com.baselibrary.data.model.BaseResp
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


    override fun submitOrder(order: Order): Observable<BaseResp<String>> {
        return repository.submitOrder(order)
    }


    @Inject
    lateinit var repository: OrderRepository


}