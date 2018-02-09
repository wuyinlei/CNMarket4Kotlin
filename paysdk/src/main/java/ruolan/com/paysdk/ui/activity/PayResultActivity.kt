package ruolan.com.paysdk.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import com.kotlin.base.utils.YuanFenConverter
import com.ruolan.factory.router.RouterPath
import kotlinx.android.synthetic.main.activity_pay_result.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import ruolan.com.baselibrary.common.BaseConstants
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity
import ruolan.com.paysdk.R
import ruolan.com.paysdk.injection.component.DaggerPayComponent
import ruolan.com.paysdk.injection.module.PayModule
import ruolan.com.paysdk.presenter.PayPresenter
import ruolan.com.paysdk.presenter.view.PayView

@Route(path = RouterPath.PayCenter.PAY_PATH)
class PayResultActivity : BaseMvpActivity<PayPresenter>(), PayView, View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_result)
        //必须要这个  要不会出现错误
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)
        initView()
        initData()

        ARouter.getInstance().inject(this)
    }


    //订单号
    var mOrderId: Int = 0
    //订单总价格
    var mTotalPrice: Long = 0


    private fun initData() {
        mOrderId = intent.getIntExtra(BaseConstants.KEY_ORDER_ID, -1)
        mTotalPrice = intent.getLongExtra(BaseConstants.KEY_ORDER_PRICE, -1)
        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)

        mPresenter.getPaySign(mOrderId, mTotalPrice)
    }

    private fun initView() {
        mAlipayTypeTv.isSelected = true
        mAlipayTypeTv.onClick(this)
        mWeixinTypeTv.onClick(this)
        mBankCardTypeTv.onClick(this)
        mPayBtn.onClick(this)

    }

    override fun onGetSignResult(result: String) {
        doAsync {
            val resultMap: Map<String, String> = PayTask(this@PayResultActivity).payV2(result, true)
            uiThread {
                if (resultMap["resultStatus"].equals("9000")) {
                    mPresenter.payOrder(mOrderId)
                } else {
                    toast("支付失败${resultMap["memo"]}")
                }
            }
        }
    }

    override fun onPayOrderResult(result: Boolean) {
        toast("支付成功")
        finish()
    }

    override fun injectComponent() {
        DaggerPayComponent.builder()
                .activityComponent(mActivityComponent)
                .payModule(PayModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mAlipayTypeTv -> {
                updatePayType(true, false, false)
            }
            R.id.mWeixinTypeTv -> {
                updatePayType(false, true, false)
            }
            R.id.mBankCardTypeTv -> {
                updatePayType(false, false, true)
            }
            R.id.mPayBtn -> {
                mPresenter.getPaySign(mOrderId, mTotalPrice)
            }
        }
    }

    /*
       选择支付类型，UI变化
    */
    private fun updatePayType(isAliPay: Boolean, isWeixinPay: Boolean, isBankCardPay: Boolean) {
        mAlipayTypeTv.isSelected = isAliPay
        mWeixinTypeTv.isSelected = isWeixinPay
        mBankCardTypeTv.isSelected = isBankCardPay
    }
}
