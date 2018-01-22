package com.ruolan.user.ui.activity

import android.os.Bundle
import android.view.View
import com.ruolan.user.R
import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.injection.component.DaggerUserComponent
import com.ruolan.user.injection.module.UserModule
import com.ruolan.user.presenter.LoginPresenter
import com.ruolan.user.presenter.view.LoginView
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
class LoginActivity: BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {

        mLoginBtn.enable(mMobileEt,{isBtnEnable()})
        mLoginBtn.enable(mPwdEt,{isBtnEnable()})

        mLoginBtn.onClick(this)
        mHeaderBar.getRightView().onClick(this)
        mForgetPwdTv.onClick(this)

    }

    /*
          点击事件
       */
    override fun onClick(view: View) {
        when(view.id){
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }

            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString(),"")
            }

        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this;
    }



    override fun onLoginResult(userInfo: UserInfo) {
        toast("登录成功")
    }

    /*
         判断按钮是否可用
      */
    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()
    }
}