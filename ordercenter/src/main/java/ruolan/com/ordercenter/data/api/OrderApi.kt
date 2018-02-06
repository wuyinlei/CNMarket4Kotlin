package ruolan.com.ordercenter.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.ordercenter.data.protocol.SubmitOrderReq
import rx.Observable

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function 订单相关api
 *
 */
interface OrderApi{
    /*
       确认订单
    */
    @POST("order/confirm")
    fun confirmOrder(@Body req: SubmitOrderReq): Observable<BaseResp<String>>


}