package ruolan.com.ordercenter.service.impl

import ruolan.com.baselibrary.ext.convert
import ruolan.com.baselibrary.ext.convertBoolean
import ruolan.com.ordercenter.data.protocol.ShipAddress
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


    /**
     * 获取地址列表
     */
    override fun getShipAddressList(): Observable<MutableList<ShipAddress>> {
        return repository.getShipAddressList().convert()
    }

    /**
     * 更新收货地址
     */
    override fun modifyShipAddress(address: ShipAddress): Observable<Boolean> {
        return repository.modifyShipAddress(address).convertBoolean()
    }

    /**
     * 添加收货地址
     */
    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean> {
        return repository.addShipAddress(shipUserName, shipUserMobile, shipAddress).convertBoolean()
    }

    /**
     * 删除收货地址
     */
    override fun deleteShipAddress(id: Int): Observable<Boolean> {
        return repository.deleteShipAddress(id).convertBoolean()
    }

    @Inject
    lateinit var repository: ShipAddressRepository


}