package ruolan.com.ordercenter.ui.activity

import android.os.Bundle
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity
import ruolan.com.ordercenter.R
import ruolan.com.ordercenter.injection.component.DaggerOrderComponent
import ruolan.com.ordercenter.injection.module.OrderModule
import ruolan.com.ordercenter.presenter.OrderConfirmPresenter
import ruolan.com.ordercenter.presenter.view.OrderConfirmView

class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder()
                .activityComponent(mActivityComponent)
                .orderModule(OrderModule())
                .build()
                .inject(this)
    }

}
