package ruolan.com.ordercenter.presenter

import ruolan.com.baselibrary.presenter.BasePresenter
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

}