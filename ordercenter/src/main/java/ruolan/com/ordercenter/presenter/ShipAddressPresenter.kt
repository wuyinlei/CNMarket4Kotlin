package ruolan.com.ordercenter.presenter

import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.ordercenter.data.protocol.ShipAddress
import ruolan.com.ordercenter.presenter.view.ShipAddressView
import ruolan.com.ordercenter.service.ShipAddressService
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    fun getShipAddressLit() {
        shipAddressService.getShipAddressList()
                .excute(object : BaseSubscriber<MutableList<ShipAddress>>(mView) {
                    override fun onNext(t: MutableList<ShipAddress>) {
                        mView.onGetShipAddressResult(t)
                    }
                }, lifecycleProvider)
    }

    /*
        设置默认收货人信息
     */
    fun setDefaultShipAddress(address:ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.modifyShipAddress(address).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSetDefaultResult(t)
            }
        }, lifecycleProvider)

    }

    /**
     * 删除收货地址
     */
    fun deleteShipAddress(id:Int){
        if (!checkNetWork()) {
            return
        }
        shipAddressService.deleteShipAddress(id)
                .excute(object : BaseSubscriber<Boolean>(mView){
                    override fun onNext(t: Boolean) {
                        mView.onDeleteResult(t)
                    }
                },lifecycleProvider)
    }

}