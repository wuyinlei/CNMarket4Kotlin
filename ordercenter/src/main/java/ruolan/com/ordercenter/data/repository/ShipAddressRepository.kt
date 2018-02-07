package ruolan.com.ordercenter.data.repository

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import ruolan.com.ordercenter.data.api.OrderApi
import ruolan.com.ordercenter.data.api.ShipAddressApi
import ruolan.com.ordercenter.data.protocol.AddShipAddressReq
import ruolan.com.ordercenter.data.protocol.GetOrderReq
import ruolan.com.ordercenter.data.protocol.Order
import ruolan.com.ordercenter.data.protocol.SubmitOrderReq
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


}