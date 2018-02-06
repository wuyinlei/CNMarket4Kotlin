package ruolan.com.paysdk.data.repository

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import ruolan.com.paysdk.data.api.PayApi
import ruolan.com.paysdk.data.protocol.GetPaySignReq
import ruolan.com.paysdk.data.protocol.PayOrderReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class PayRespository @Inject constructor() {

    /*
       获取支付宝支付签名
    */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<BaseResp<String>> {
        return RetrofitFactory.instance
                .create(PayApi::class.java)
                .getPaySign(GetPaySignReq(orderId,totalPrice))
    }

    /*
        刷新订单状态，已支付
     */
    fun payOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance
                .create(PayApi::class.java)
                .payOrder(PayOrderReq(orderId))
    }

}