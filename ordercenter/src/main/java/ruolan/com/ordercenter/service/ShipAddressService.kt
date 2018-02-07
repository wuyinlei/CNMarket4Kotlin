package ruolan.com.ordercenter.service

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.ordercenter.data.protocol.Order
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



}