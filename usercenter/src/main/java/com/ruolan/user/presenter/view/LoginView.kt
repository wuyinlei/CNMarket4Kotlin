package com.ruolan.user.presenter.view

import com.ruolan.user.data.model.UserInfo
import ruolan.com.baselibrary.presenter.view.BaseView

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
interface LoginView : BaseView {

    fun onLoginResult(userInfo: UserInfo)
}