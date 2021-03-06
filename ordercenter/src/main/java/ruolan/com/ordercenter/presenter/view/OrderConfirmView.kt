package ruolan.com.ordercenter.presenter.view

import ruolan.com.baselibrary.presenter.view.BaseView
import ruolan.com.ordercenter.data.protocol.Order

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
interface OrderConfirmView : BaseView{

    fun onOrderResult(order: Order)

    fun onSubmitResult(boolean: Boolean)

}