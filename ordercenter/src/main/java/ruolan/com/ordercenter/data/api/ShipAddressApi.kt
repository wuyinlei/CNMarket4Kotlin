package ruolan.com.ordercenter.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.ordercenter.data.protocol.AddShipAddressReq
import ruolan.com.ordercenter.data.protocol.DeleteShipAddressReq
import ruolan.com.ordercenter.data.protocol.ModifyShipAddressReq
import ruolan.com.ordercenter.data.protocol.ShipAddress
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

    /**
     * 获取收货地址列表
     */
    @POST("kotlinserver/address/getlist")
    fun getShipAddressList():Observable<BaseResp<MutableList<ShipAddress>>>

    /**
     * 更新用户地址
     */
    @POST("kotlinserver/address/modify")
    fun modifyShipAddress(@Body req: ModifyShipAddressReq):Observable<BaseResp<String>>


    /**
     * 删除用户地址
     */
    @POST("kotlinserver/address/delete")
    fun deleteShipAddress(@Body req: DeleteShipAddressReq): Observable<BaseResp<String>>



}