package ruolan.com.goodscenter.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.goodscenter.data.protocol.GetGoodsDetail
import ruolan.com.goodscenter.data.protocol.GetGoodsListReq
import ruolan.com.goodscenter.data.protocol.Goods
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
interface GoodsApi {


    @POST("kotlinserver/goods/getgoodslist")
    fun getGoods(@Body req: GetGoodsListReq): Observable<BaseResp<MutableList<Goods>>>


    @POST("kotlinserver/goods/getgoodsdetail")
    fun getGoodsDetail(@Body req: GetGoodsDetail):Observable<BaseResp<Goods>>


}