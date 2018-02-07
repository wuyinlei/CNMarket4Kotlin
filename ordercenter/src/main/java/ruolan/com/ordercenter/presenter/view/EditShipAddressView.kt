package ruolan.com.ordercenter.presenter.view

import ruolan.com.baselibrary.presenter.view.BaseView

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
interface EditShipAddressView : BaseView {

    //添加收货人回调
    fun onAddShipAddressResult(result: Boolean)

    //修改收货人回调
    fun onEditShipAddressResult(result: Boolean)

}