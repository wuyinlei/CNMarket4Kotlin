package ruolan.com.ordercenter.presenter

import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.ordercenter.data.protocol.ShipAddress
import ruolan.com.ordercenter.presenter.view.EditShipAddressView
import ruolan.com.ordercenter.service.ShipAddressService
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    /**
     * 添加地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNetWork())
            return

        shipAddressService.addShipAddress(shipUserName, shipUserMobile, shipAddress)
                .excute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onAddShipAddressResult(t)
                    }
                }, lifecycleProvider)

    }

    /**
     * 编辑地址
     */
    fun editShipAddress(mAddress: ShipAddress) {


    }

}