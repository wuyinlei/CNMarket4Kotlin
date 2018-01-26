package com.ruolan.user.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ruolan.user.R
import com.ruolan.user.injection.component.DaggerUserComponent.builder
import com.ruolan.user.injection.module.UserModule
import com.ruolan.user.presenter.ForgetPwdPresenter
import com.ruolan.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.ext.enable
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity

/**
 * Created by wuyinlei on 2018/1/22.
 *
 * @function
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()

    }

    /*
        初始化视图
     */
    private fun initView() {

        mNextBtn.enable(mMobileEt, { isBtnEnable() })
        mNextBtn.enable(mVerifyCodeEt, { isBtnEnable() })

        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)
    }

    private fun isBtnEnable(): Boolean {

        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mNextBtn -> {
                //跳转
                mPresenter.forgetPwd(mMobileEt.text.toString(),mVerifyCodeEt.text.toString())
            }

            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证成功")
            }
        }
    }

    override fun onForgetResult(boolean: Boolean) {
        toast("验证成功")
        val intent  = Intent(this,ResetPwdActivity::class.java)
        intent.putExtra("mobile",mMobileEt.text.toString().trim())
        startActivity(intent)
    }

    override fun injectComponent() {
        builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }
}