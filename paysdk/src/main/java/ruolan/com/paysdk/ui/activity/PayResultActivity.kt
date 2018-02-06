package ruolan.com.paysdk.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ruolan.factory.router.RouterPath
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
    }

    override fun onGetSignResult(result: String) {

    }

    override fun onPayOrderResult(result: Boolean) {

    }

    override fun injectComponent() {
        DaggerPayComponent.builder()
                .activityComponent(mActivityComponent)
                .payModule(PayModule())
                .build()
                .inject(this)
    }
}
