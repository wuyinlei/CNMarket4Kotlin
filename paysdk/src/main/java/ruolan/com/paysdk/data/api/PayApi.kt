package ruolan.com.paysdk.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.paysdk.data.protocol.GetPaySignReq
import ruolan.com.paysdk.data.protocol.PayOrderReq
import rx.Observable

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */

interface PayApi{

    /*
       获取支付宝支付签名
    */
    @POST("pay/getPaySign")
    fun getPaySign(@Body req: GetPaySignReq): Observable<BaseResp<String>>

    /*
        刷新订单状态，已支付
     */
    @POST("order/pay")
    fun payOrder(@Body req: PayOrderReq): Observable<BaseResp<String>>
}
