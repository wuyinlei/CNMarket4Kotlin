package com.ruolan.factory.common

import com.alibaba.android.arouter.launcher.ARouter
import com.ruolan.factory.router.RouterPath
import ruolan.com.baselibrary.common.BaseConstants
import ruolan.com.baselibrary.utils.AppPrefsUtils

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */

/*
    顶级函数，判断是否登录
 */
fun isLogined(): Boolean {
    return AppPrefsUtils.getString(BaseConstants.KEY_SP_TOKEN).isNotEmpty()
}

/*
    如果已经登录，进行传入的方法处理
    如果没有登录，进入登录界面
 */
fun afterLogin(method: () -> Unit) {
    if (isLogined()) {
        method()
    } else {
        //需要去登录
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }
}
