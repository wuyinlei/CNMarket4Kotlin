package ruolan.com.ordercenter.service

import ruolan.com.ordercenter.data.protocol.ShipAddress
import rx.Observable

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
interface ShipAddressService {


    /**
     * 添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean>

    /**
     * 获取地址列表
     */
    fun getShipAddressList():Observable<MutableList<ShipAddress>>

    /**
     * 修改收货地址
     */
    fun modifyShipAddress(address: ShipAddress):Observable<Boolean>

    /**
     * 删除收货地址
     */
    fun deleteShipAddress(id:Int):Observable<Boolean>

}