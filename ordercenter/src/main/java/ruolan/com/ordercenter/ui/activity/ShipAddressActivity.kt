package ruolan.com.ordercenter.ui.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ship_address.*
import org.jetbrains.anko.startActivity
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity
import ruolan.com.ordercenter.R
import ruolan.com.ordercenter.data.protocol.ShipAddress
import ruolan.com.ordercenter.injection.component.DaggerShipAddressComponent
import ruolan.com.ordercenter.injection.module.ShipAddressModule
import ruolan.com.ordercenter.presenter.ShipAddressPresenter
import ruolan.com.ordercenter.presenter.view.ShipAddressView

class ShipAddressActivity : BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {

    override fun onSetDefaultResult(result: Boolean) {

    }

    override fun onDeleteResult(result: Boolean) {

    }

    override fun onGetShipAddressResult(result: MutableList<ShipAddress>?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ship_address)
        initView()
    }

    private fun initView() {

        mAddAddressBtn.onClick {
            startActivity<EditShipAddressActivity>()
        }
    }


    override fun injectComponent() {
        DaggerShipAddressComponent.builder()
                .activityComponent(mActivityComponent)
                .shipAddressModule(ShipAddressModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

}
