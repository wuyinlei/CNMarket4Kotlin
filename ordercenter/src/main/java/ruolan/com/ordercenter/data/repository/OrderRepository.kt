package ruolan.com.ordercenter.data.repository

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import ruolan.com.ordercenter.data.api.OrderApi
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

    fun submitOrder(order: Order): Observable<BaseResp<String>> {
        return RetrofitFactory.instance
                .create(OrderApi::class.java)
                .confirmOrder(SubmitOrderReq(order))
    }

}