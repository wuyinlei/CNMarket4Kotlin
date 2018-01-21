package com.ruolan.user.ui.activity

import android.os.Bundle
import android.view.View
import com.ruolan.user.R
import com.ruolan.user.injection.component.DaggerUserComponent
import com.ruolan.user.injection.module.UserModule
import ruolan.com.baselibrary.ext.enable
import ruolan.com.baselibrary.ext.onClick
import com.ruolan.user.presenter.RegisterPresenter
import com.ruolan.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity

/**
 * Created by wuyinlei on 2018/1/17.
 *
 * @function 注册界面
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {


    override fun onRegisterResult(boolean: Boolean) {
        toast("注册成功")
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mRegisterBtn -> {
                mPresenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initInjection()

        initView()


    }


    fun initInjection() {

        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    private fun initView() {


        mRegisterBtn.enable(mMobileEt, { isBtnEnable() })
        mRegisterBtn.enable(mVerifyCodeEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdConfirmEt, { isBtnEnable() })

        mVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)

    }

    /*
       判断按钮是否可用
    */
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

}