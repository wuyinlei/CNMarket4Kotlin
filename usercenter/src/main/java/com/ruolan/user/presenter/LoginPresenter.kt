package com.ruolan.user.presenter

import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.presenter.view.LoginView
import com.ruolan.user.presenter.view.RegisterView
import com.ruolan.user.service.impl.UserServiceImpl
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {


    @Inject
    lateinit var userService : UserServiceImpl

    fun login(mobile: String, password: String, pushId: String) {

        userService.login(mobile,password,pushId)
                .excute(object : BaseSubscriber<BaseResp<UserInfo>>(mView) {
                    override fun onNext(t: BaseResp<UserInfo>) {
                        mView.onLoginResult(t.data)
                    }
                },lifecycleProvider)

    }
}