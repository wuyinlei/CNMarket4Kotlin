package com.ruolan.user.presenter.view

import com.ruolan.user.data.model.UserInfo
import ruolan.com.baselibrary.presenter.view.BaseView

/**
 * Created by wuyinlei on 2018/1/22.
 *
 * @function
 */
interface UserInfoView:BaseView {
    /*
       获取上传凭证回调
    */
    fun onGetUploadTokenResult(result:String)

    /*
        编辑用户资料回调
     */
    fun onEditUserResult(result: UserInfo)
}