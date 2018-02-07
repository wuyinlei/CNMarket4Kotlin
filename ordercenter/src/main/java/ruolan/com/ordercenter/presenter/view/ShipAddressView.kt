package ruolan.com.ordercenter.presenter.view

import ruolan.com.baselibrary.presenter.view.BaseView
import ruolan.com.ordercenter.data.protocol.ShipAddress

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
interface ShipAddressView : BaseView {

    //获取收货人列表回调
    fun onGetShipAddressResult(result: MutableList<ShipAddress>?)

    //设置默认收货人回调
    fun onSetDefaultResult(result: Boolean)

    //删除收货人回调
    fun onDeleteResult(result: Boolean)

}