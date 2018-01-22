package com.ruolan.user.presenter

import com.ruolan.user.presenter.view.UserInfoView
import com.ruolan.user.service.impl.UserServiceImpl
import ruolan.com.baselibrary.presenter.BasePresenter
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
class UserInofPresenter @Inject constructor() : BasePresenter<UserInfoView>() {


    @Inject
    lateinit var userService : UserServiceImpl


    /*
     获取七牛云上传凭证
  */
    fun getUploadToken(){
        if (!checkNetWork())
            return
        mView.showLoading()

    }

    /*
       编辑用户资料
    */
    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String){
        if (!checkNetWork())
            return

        mView.showLoading()


    }


}