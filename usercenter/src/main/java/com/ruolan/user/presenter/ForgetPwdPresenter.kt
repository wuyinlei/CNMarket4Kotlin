package com.ruolan.user.presenter

import com.ruolan.user.presenter.view.ForgetPwdView
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
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService : UserServiceImpl


    fun forgetPwd(mobile: String, code: String) {

        userService.forgetPwd(mobile,code).excute(object :BaseSubscriber<Boolean>(mView){
            override fun onNext(t: Boolean) {
                mView.onForgetResult(t)
            }
        },lifecycleProvider)
    }
}