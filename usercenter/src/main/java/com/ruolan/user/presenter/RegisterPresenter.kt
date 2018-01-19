package com.ruolan.user.presenter

import com.ruolan.user.presenter.view.RegisterView
import ruolan.com.baselibrary.presenter.BasePresenter

/**
 * Created by wuyinlei on 2018/1/19.
 */
class RegisterPresenter : BasePresenter<RegisterView>(){

    fun register( mobile:String,verifyCode:String,password:String){

        /**
         * 处理业务逻辑
         */


        mView.onRegisterResult(true)



    }
}