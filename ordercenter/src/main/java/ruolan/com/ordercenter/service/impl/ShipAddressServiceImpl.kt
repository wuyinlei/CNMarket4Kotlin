package ruolan.com.ordercenter.service.impl

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.convertBoolean
import ruolan.com.ordercenter.data.protocol.Order
import ruolan.com.ordercenter.data.repository.OrderRepository
import ruolan.com.ordercenter.data.repository.ShipAddressRepository
import ruolan.com.ordercenter.service.ShipAddressService
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
class ShipAddressServiceImpl @Inject constructor() : ShipAddressService {

    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean> {
        return repository.addShipAddress(shipUserName, shipUserMobile, shipAddress).convertBoolean()
    }

    @Inject
    lateinit var repository: ShipAddressRepository


}