package com.ruolan.user.service

import com.ruolan.user.data.model.UserInfo
import ruolan.com.baselibrary.data.model.BaseResp
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
interface UserService {

    //用户注册
    fun register(mobile:String,pwd:String,verifyCode:String): Observable<Boolean>

    //用户注册
    fun login(mobile:String,password:String,pushId:String): Observable<BaseResp<UserInfo>>

}