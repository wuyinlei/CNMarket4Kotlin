package com.ruolan.user.data.repository

import com.ruolan.user.data.api.UserApi
import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.data.protocol.LoginReq
import com.ruolan.user.data.protocol.RegisterReq
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class UserRespository @Inject constructor(){

    fun register(mobile:String,pwd:String,verifyCode:String):Observable<BaseResp<UserInfo>>{
        return RetrofitFactory.instance.create(UserApi::class.java).register(RegisterReq(mobile,pwd,verifyCode))
    }

    fun login(mobile:String,password:String,pushId:String):Observable<BaseResp<UserInfo>>{
        return RetrofitFactory.instance.create(UserApi::class.java).login(LoginReq(mobile,password,pushId))
    }


}