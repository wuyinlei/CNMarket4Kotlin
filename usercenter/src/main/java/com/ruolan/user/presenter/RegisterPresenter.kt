package com.ruolan.user.presenter

import com.ruolan.user.presenter.view.RegisterView
import com.ruolan.user.service.impl.UserServiceImpl
import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {


    @Inject
    lateinit var userService :UserServiceImpl

    fun register(mobile: String, verifyCode: String, password: String) {

        userService.register(mobile, password, verifyCode)
                .excute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }
                })


    }
}

