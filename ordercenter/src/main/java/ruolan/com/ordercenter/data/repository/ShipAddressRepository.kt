package ruolan.com.ordercenter.data.repository

import android.support.v4.app.ServiceCompat
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import ruolan.com.ordercenter.data.api.OrderApi
import ruolan.com.ordercenter.data.api.ShipAddressApi
import ruolan.com.ordercenter.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
class ShipAddressRepository @Inject constructor() {

    /**
     * 添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance
                .create(ShipAddressApi::class.java)
                .addShipAddress(AddShipAddressReq(shipUserName, shipUserMobile, shipAddress))
    }

    /**
     * 修改收货地址
     */
    fun modifyShipAddress(address: ShipAddress): Observable<BaseResp<String>> {
        return RetrofitFactory.instance
                .create(ShipAddressApi::class.java)
                .modifyShipAddress(ModifyShipAddressReq(address.id,
                        address.shipUserName,
                        address.shipUserMobile,
                        address.shipAddress,
                        address.shipIsDefault))
    }

    /**
     * 获取地址列表
     */
    fun getShipAddressList(): Observable<BaseResp<MutableList<ShipAddress>>> {
        return RetrofitFactory.instance
                .create(ShipAddressApi::class.java)
                .getShipAddressList()
    }

    /**
     * 删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance
                .create(ShipAddressApi::class.java)
                .deleteShipAddress(DeleteShipAddressReq(id))
    }


}