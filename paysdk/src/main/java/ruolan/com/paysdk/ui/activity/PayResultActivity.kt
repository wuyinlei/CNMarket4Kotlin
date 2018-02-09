package ruolan.com.paysdk.ui.activity

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.utils.YuanFenConverter
import com.ruolan.factory.router.RouterPath
import kotlinx.android.synthetic.main.activity_pay_result.*
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.common.BaseConstants
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity
import ruolan.com.paysdk.R
import ruolan.com.paysdk.injection.component.DaggerPayComponent
import ruolan.com.paysdk.injection.module.PayModule
import ruolan.com.paysdk.presenter.PayPresenter
import ruolan.com.paysdk.presenter.view.PayView

@Route(path = RouterPath.PayCenter.PAY_PATH)
class PayResultActivity : BaseMvpActivity<PayPresenter>(),PayView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_result)
        initView()
        initData()

        ARouter.getInstance().inject(this)
    }


    //订单号
    var mOrderId:Int = 0
    //订单总价格
    var mTotalPrice:Long = 0


    private fun initData() {
        mOrderId = intent.getIntExtra(BaseConstants.KEY_ORDER_ID,-1)
        mTotalPrice = intent.getLongExtra(BaseConstants.KEY_ORDER_PRICE,-1)
        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)

        mPresenter.getPaySign(mOrderId,mTotalPrice)
    }

    private fun initView() {


    }

    override fun onGetSignResult(result: String) {
        toast(result)
        Log.d("onGetSignResult","接收到的支付sign是：" + result)
    }

    override fun onPayOrderResult(result: Boolean) {

    }

    override fun injectComponent() {
        DaggerPayComponent.builder()
                .activityComponent(mActivityComponent)
                .payModule(PayModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }
}
