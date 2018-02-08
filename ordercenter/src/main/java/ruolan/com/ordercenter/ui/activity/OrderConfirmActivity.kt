package ruolan.com.ordercenter.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.utils.YuanFenConverter
import com.ruolan.factory.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.common.BaseConstants
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ext.setVisible
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity
import ruolan.com.ordercenter.R
import ruolan.com.ordercenter.data.protocol.Order
import ruolan.com.ordercenter.event.SelectAddressEvent
import ruolan.com.ordercenter.injection.component.DaggerOrderComponent
import ruolan.com.ordercenter.injection.module.OrderModule
import ruolan.com.ordercenter.presenter.OrderConfirmPresenter
import ruolan.com.ordercenter.presenter.view.OrderConfirmView
import ruolan.com.ordercenter.ui.adapter.OrderGoodsAdapter

@Route(path = RouterPath.OrderCenter.ORDER_PATH)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {


    @Autowired(name = BaseConstants.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0

    private lateinit var mAdapter: OrderGoodsAdapter
    private var mCurrentOrder: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)
        initObserve()
        initView()
        loadData()
    }

    /*
       初始化视图
    */
    private fun initView() {
        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }
        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }

        mSubmitOrderBtn.onClick {
            mCurrentOrder?.let {
                if (mShipView.visibility == View.VISIBLE) {
                    mPresenter.submitOrder(mCurrentOrder!!)
                } else {
                    toast("请填写地址信息")
                }
            }
        }

        //订单中商品列表
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }


    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    @SuppressLint("SetTextI18n")
    override fun onOrderResult(order: Order) {
        mCurrentOrder = order
        mAdapter.setData(order.orderGoodsList)
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(order.totalPrice)}"
        updateAddressView()

    }

    @SuppressLint("SetTextI18n")
    private fun updateAddressView() {

        mCurrentOrder?.let {
            if (it.shipAddress == null) {
                mSelectShipTv.setVisible(true)
                mShipView.setVisible(false)
            } else {
                mSelectShipTv.setVisible(false)
                mShipView.setVisible(true)

                mShipNameTv.text = it.shipAddress!!.shipUserName + "  " +
                        it.shipAddress!!.shipUserMobile
                mShipAddressTv.text = it.shipAddress!!.shipAddress
            }
        }
    }

    /*
       初始化选择收货人事件监听
    */
    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
                .subscribe { t: SelectAddressEvent ->
                    run {
                        mCurrentOrder?.let {
                            it.shipAddress = t.address
                        }
                        updateAddressView()
                    }
                }
                .registerInBus(this)

    }

    /**
     * 订单提交成功的回调
     */
    override fun onSubmitResult(boolean: Boolean) {
        toast("订单提交成功")
        ARouter.getInstance().build(RouterPath.PayCenter.PAY_PATH)
                .withInt(BaseConstants.KEY_ORDER_ID,mCurrentOrder!!.id)
                .withLong(BaseConstants.KEY_ORDER_PRICE,mCurrentOrder!!.totalPrice)
                .navigation()
        finish()
    }


    override fun injectComponent() {
        DaggerOrderComponent.builder()
                .activityComponent(mActivityComponent)
                .orderModule(OrderModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    /*
        取消事件监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}
