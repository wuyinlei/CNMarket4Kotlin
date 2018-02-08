package ruolan.com.ordercenter.data.repository

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import ruolan.com.ordercenter.data.api.OrderApi
import ruolan.com.ordercenter.data.protocol.GetOrderReq
import ruolan.com.ordercenter.data.protocol.Order
import ruolan.com.ordercenter.data.protocol.SubmitOrderReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
class OrderRepository @Inject constructor() {

    /**
     * 提交订单
     */
    fun submitOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance
                .create(OrderApi::class.java)
                .confirmOrder(SubmitOrderReq(orderId))
    }

    /**
     * 获取订单
     */
    fun getOrderById(orderId:Int):Observable<BaseResp<Order>>{
        return RetrofitFactory.instance
                .create(OrderApi::class.java)
                .getOrderById(GetOrderReq(orderId))
    }

}