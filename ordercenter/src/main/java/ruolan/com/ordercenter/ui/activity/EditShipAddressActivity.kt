package ruolan.com.ordercenter.ui.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ship_address_edit.*
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.common.BaseConstants
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity
import ruolan.com.ordercenter.R
import ruolan.com.ordercenter.data.protocol.ShipAddress
import ruolan.com.ordercenter.injection.component.DaggerShipAddressComponent
import ruolan.com.ordercenter.injection.module.ShipAddressModule
import ruolan.com.ordercenter.presenter.EditShipAddressPresenter
import ruolan.com.ordercenter.presenter.view.EditShipAddressView

class EditShipAddressActivity : BaseMvpActivity<EditShipAddressPresenter>(),EditShipAddressView {


    private var mAddress: ShipAddress? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ship_address_edit)
        initView()
        initData()
    }


    override fun injectComponent() {
        DaggerShipAddressComponent.builder()
                .activityComponent(mActivityComponent)
                .shipAddressModule(ShipAddressModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    /*
       初始化视图
    */
    private fun initView() {

        mSaveBtn.onClick {
            if (mShipNameEt.text.isNullOrEmpty()){
                toast("名称不能为空")
                return@onClick
            }
            if (mShipMobileEt.text.isNullOrEmpty()){
                toast("电话不能为空")
                return@onClick
            }
            if (mShipAddressEt.text.isNullOrEmpty()){
                toast("地址不能为空")
                return@onClick
            }
            if (mAddress == null) {
                mPresenter.addShipAddress(mShipNameEt.text.toString(),
                        mShipMobileEt.text.toString(),
                        mShipAddressEt.text.toString())
            }else{
                mAddress!!.shipUserName = mShipNameEt.text.toString()
                mAddress!!.shipUserMobile = mShipMobileEt.text.toString()
                mAddress!!.shipAddress = mShipAddressEt.text.toString()
                mPresenter.editShipAddress(mAddress!!)
            }
        }
    }

    /*
        初始化数据
     */
    private fun initData() {

        mAddress = intent.getParcelableExtra(BaseConstants.KEY_SHIP_ADDRESS)

        mAddress?.let {
            mShipNameEt.setText(it.shipUserName)
            mShipMobileEt.setText(it.shipUserMobile)
            mShipAddressEt.setText(it.shipAddress)
        }

    }


    override fun onAddShipAddressResult(result: Boolean) {
        toast("地址添加成功")
        finish()
    }

    override fun onEditShipAddressResult(result: Boolean) {

    }

}
