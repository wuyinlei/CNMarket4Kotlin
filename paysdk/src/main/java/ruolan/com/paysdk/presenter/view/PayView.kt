package ruolan.com.paysdk.presenter.view

import ruolan.com.baselibrary.presenter.view.BaseView

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
interface PayView:BaseView {

    //获取支付签名
    fun onGetSignResult(result: String)
    //同步支付成功状态
    fun onPayOrderResult(result: Boolean)

}