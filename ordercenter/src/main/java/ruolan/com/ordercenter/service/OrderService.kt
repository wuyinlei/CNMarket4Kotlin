package ruolan.com.ordercenter.service

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.ordercenter.data.protocol.Order
import rx.Observable

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
interface OrderService {


    /**
     * 提交订单
     */
    fun submitOrder(order: Order): Observable<Boolean>

    /**
     * 获取到订单
     */
    fun getOrderById(orderId: Int): Observable<BaseResp<Order>>


}