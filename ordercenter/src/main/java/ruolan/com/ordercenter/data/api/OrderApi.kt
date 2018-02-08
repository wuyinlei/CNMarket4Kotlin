package ruolan.com.ordercenter.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.ordercenter.data.protocol.GetOrderListReq
import ruolan.com.ordercenter.data.protocol.GetOrderReq
import ruolan.com.ordercenter.data.protocol.Order
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
    @POST("kotlinserver/order/confirm")
    fun confirmOrder(@Body req: SubmitOrderReq): Observable<BaseResp<String>>


    @POST("kotlinserver/order/getOrderById")
    fun getOrderById(@Body req: GetOrderReq): Observable<BaseResp<Order>>


    /*
       根据订单状态查询查询订单列表
    */
    @POST("kotlinserver/order/getOrderList")
    fun getOrderList(@Body req: GetOrderListReq): Observable<BaseResp<MutableList<Order>>>


}