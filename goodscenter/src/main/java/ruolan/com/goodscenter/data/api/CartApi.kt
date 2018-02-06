package ruolan.com.goodscenter.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.goodscenter.data.protocol.AddCartGoodsReq
import ruolan.com.goodscenter.data.protocol.CartGoods
import ruolan.com.goodscenter.data.protocol.DeleteCartGoodsReq
import ruolan.com.goodscenter.data.protocol.SubmitCartReq
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
interface CartApi {

    @POST("kotlinserver/cartgoods/getList")
    fun getCartGoods(): Observable<BaseResp<MutableList<CartGoods>>>


    @POST("kotlinserver/cartgoods/add")
    fun addCartGoods(@Body req: AddCartGoodsReq): Observable<BaseResp<Int>>


    @POST("kotlinserver/cartgoods/delete")
    fun delCartGoods(@Body req: DeleteCartGoodsReq): Observable<BaseResp<String>>

    @POST("kotlinserver/cartgoods/submit")
    fun submitCartGoods(@Body req: SubmitCartReq): Observable<BaseResp<Int>>


}