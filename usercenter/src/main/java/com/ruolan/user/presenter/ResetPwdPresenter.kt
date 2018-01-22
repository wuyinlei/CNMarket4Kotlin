package com.ruolan.user.presenter

import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.presenter.view.LoginView
import com.ruolan.user.presenter.view.RegisterView
import com.ruolan.user.presenter.view.ResetPwdView
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
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {


    @Inject
    lateinit var userService: UserServiceImpl


    //
    fun resetPwd(mobile: String, newPassword: String) {

        if (!checkNetWork())
            return
        userService.resetPwd(mobile, newPassword)
                .excute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onResetPwdResult(t)
                    }
                }, lifecycleProvider)

    }
}