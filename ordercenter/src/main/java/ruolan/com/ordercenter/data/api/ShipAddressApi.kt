package ruolan.com.ordercenter.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.ordercenter.data.protocol.AddShipAddressReq
import rx.Observable

/**
 * Created by wuyinlei on 2018/2/7.
 *
 * @function  地址管理API
 */
interface ShipAddressApi {

    /*
       添加收货地址
    */
    @POST("kotlinserver/address/add")
    fun addShipAddress(@Body req: AddShipAddressReq): Observable<BaseResp<String>>


}