package com.ruolan.user.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.ruolan.factory.PushProvider
import com.ruolan.factory.router.RouterPath
import com.ruolan.user.R
import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.injection.component.DaggerUserComponent
import com.ruolan.user.injection.module.UserModule
import com.ruolan.user.presenter.LoginPresenter
import com.ruolan.user.presenter.view.LoginView
import com.ruolan.user.utils.putUserInfo
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.ext.enable
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    @Autowired(name = RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
    @JvmField
    var mPushProvider: PushProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {

        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mPwdEt, { isBtnEnable() })

        mLoginBtn.onClick(this)
        mHeaderBar.getRightView().onClick(this)
        mForgetPwdTv.onClick(this)

    }

    /*
          点击事件
       */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }

            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), mPushProvider?.getPushId() ?: "")
            }

        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }


    override fun onLoginResult(userInfo: UserInfo) {
        toast("登录成功")
        putUserInfo(userInfo)
        finish()
    }

    /*
         判断按钮是否可用
      */
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()
    }
}