package com.ruolan.user.presenter

import com.ruolan.user.presenter.view.RegisterView
import com.ruolan.user.service.impl.UserServiceImpl
import ruolan.com.baselibrary.ext.execute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function  注册的presenter
 */
class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register(mobile: String, verifyCode: String, password: String) {

        /**
         * 处理业务逻辑
         *
         */

        val userService = UserServiceImpl()
        userService.register(mobile, verifyCode, password)
                .execute(object : BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }
                })

    }
}