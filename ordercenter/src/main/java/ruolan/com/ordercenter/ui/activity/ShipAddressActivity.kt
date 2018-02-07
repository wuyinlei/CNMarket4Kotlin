package ruolan.com.ordercenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_ship_address.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.common.BaseConstants
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ext.startLoading
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity
import ruolan.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import ruolan.com.ordercenter.R
import ruolan.com.ordercenter.data.protocol.ShipAddress
import ruolan.com.ordercenter.event.SelectAddressEvent
import ruolan.com.ordercenter.injection.component.DaggerShipAddressComponent
import ruolan.com.ordercenter.injection.module.ShipAddressModule
import ruolan.com.ordercenter.presenter.ShipAddressPresenter
import ruolan.com.ordercenter.presenter.view.ShipAddressView
import ruolan.com.ordercenter.ui.adapter.ShipAddressAdapter

class ShipAddressActivity : BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {


    private lateinit var mAdapter: ShipAddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ship_address)
        initView()

    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun initData() {
        mMultiStateView.startLoading()
        mPresenter.getShipAddressLit()

    }

    private fun initView() {

        mAdapter = ShipAddressAdapter(this)
        mAddressRv.layoutManager = LinearLayoutManager(this)
        mAddressRv.adapter = mAdapter

        mAdapter.mOptClickListener =object : ShipAddressAdapter.OnOptClickListener{
            override fun onSetDefault(address: ShipAddress) {
                //设置默认地址
                mPresenter.setDefaultShipAddress(address)
            }

            override fun onEdit(address: ShipAddress) {
                //编辑地址
                startActivity<EditShipAddressActivity>(BaseConstants.KEY_SHIP_ADDRESS to address)
            }

            override fun onDelete(address: ShipAddress) {
                //删除地址
                mPresenter.deleteShipAddress(address.id)
            }

        }

        mAdapter.setOnItemClickListener(object:BaseRecyclerViewAdapter.OnItemClickListener<ShipAddress>{
            override fun onItemClick(item: ShipAddress, position: Int) {
                Bus.send(SelectAddressEvent(item))
                finish()
            }
        })

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



    override fun onSetDefaultResult(result: Boolean) {
        toast("设置默认成功")
        initData()
    }

    override fun onDeleteResult(result: Boolean) {
        toast("删除地址成功")
        initData()
    }

    override fun onGetShipAddressResult(result: MutableList<ShipAddress>?) {
        if (!result?.isEmpty()!!){
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            mAdapter.setData(result)
        } else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

}
