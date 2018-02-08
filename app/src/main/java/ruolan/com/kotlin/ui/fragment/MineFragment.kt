package ruolan.com.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruolan.factory.common.afterLogin
import com.ruolan.factory.common.isLogined
import com.ruolan.user.ui.activity.LoginActivity
import com.ruolan.user.ui.activity.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_mine.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import ruolan.com.baselibrary.common.BaseConstants
import ruolan.com.baselibrary.ext.loadUrl
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ui.fragment.BaseFragment
import ruolan.com.baselibrary.utils.AppPrefsUtils
import ruolan.com.kotlin.R
import ruolan.com.kotlin.ui.activity.SettingActivity
import ruolan.com.ordercenter.common.OrderConstant
import ruolan.com.ordercenter.common.OrderStatus
import ruolan.com.ordercenter.ui.activity.OrderActivity
import ruolan.com.ordercenter.ui.activity.ShipAddressActivity

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class MineFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_mine, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {

        if (isLogined()) {
            val userIcon = AppPrefsUtils.getString(BaseConstants.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(BaseConstants.KEY_SP_USER_NAME)
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }

    }

    /*
       初始化视图
    */
    private fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)

        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
        mAllOrderTv.onClick(this)
        mAddressTv.onClick(this)
        mShareTv.onClick(this)
        mSettingTv.onClick(this)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                if (isLogined()) {
                    startActivity<UserInfoActivity>()
                } else {
                    startActivity<LoginActivity>()
                }
            }

            R.id.mWaitPayOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
                }
            }

            R.id.mWaitConfirmOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
                }
            }

            R.id.mCompleteOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
                }
            }


            R.id.mAllOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>()
                }
            }

            R.id.mAddressTv -> {
                afterLogin {
                    startActivity<ShipAddressActivity>()
                }
            }

            R.id.mShareTv -> {
                toast(R.string.coming_soon_tip)
            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
        }


    }


}