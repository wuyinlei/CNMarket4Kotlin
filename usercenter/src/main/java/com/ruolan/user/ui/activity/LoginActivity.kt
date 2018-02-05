package com.ruolan.user.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
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
@Suppress("DEPRECATION")
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
        //这个地方加入了空动画  是为了解决当这个登录成功之后跳转到主界面的时候  会有一个app的桌面图片的界面
        //使用这个空动画是为了覆盖系统的  这个也是arouter的一个bug?还是  系统正常的跳转是不存在这个bug的
        ARouter.getInstance().build(RouterPath.MainCenter.MAIN_PATH)
                .withTransition(R.anim.anim_in,R.anim.anim_out)
                //而且这个地方必须使用当前的上下文  如果是默认的不填  也就是默认的application的上下文 是不行的
                .navigation(this)
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