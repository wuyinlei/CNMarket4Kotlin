package ruolan.com.paysdk.presenter

import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.paysdk.presenter.view.PayView
import ruolan.com.paysdk.service.PayService
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
class PayPresenter @Inject constructor() : BasePresenter<PayView>() {

    @Inject
    lateinit var service: PayService

    /*
       获取支付签名
    */
    fun getPaySign(orderId: Int, totalPrice: Long) {
        if (!checkNetWork())
            return

        service.getPaySign(orderId, totalPrice)
                .excute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onGetSignResult(t)
                    }
                }, lifecycleProvider)
    }

    /*
       订单支付，同步订单状态
    */
    fun payOrder(orderId: Int) {
        if (!checkNetWork())
            return

        service.payOrder(orderId)
                .excute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onPayOrderResult(t)
                    }
                }, lifecycleProvider)
    }


}