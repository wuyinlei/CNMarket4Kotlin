package com.ruolan.user.utils

import com.ruolan.user.data.model.UserInfo
import ruolan.com.baselibrary.common.BaseConstants
import ruolan.com.baselibrary.utils.AppPrefsUtils

/**
 * Created by wuyinlei on 2018/1/22.
 *
 * @function
 */

/*
   退出登录时，传入null,清空存储
*/
fun putUserInfo(userInfo: UserInfo?) {
    AppPrefsUtils.putString(BaseConstants.KEY_SP_TOKEN, userInfo?.token ?: "")
    AppPrefsUtils.putString(BaseConstants.KEY_SP_USER_ICON, userInfo?.userIcon ?: "")
    AppPrefsUtils.putString(BaseConstants.KEY_SP_USER_NAME, userInfo?.userName ?: "")
    AppPrefsUtils.putString(BaseConstants.KEY_SP_USER_MOBILE, userInfo?.userMobile ?: "")
    AppPrefsUtils.putString(BaseConstants.KEY_SP_USER_GENDER, userInfo?.userGender ?: "")
    AppPrefsUtils.putString(BaseConstants.KEY_SP_USER_SIGN, userInfo?.userSign ?: "")
}
