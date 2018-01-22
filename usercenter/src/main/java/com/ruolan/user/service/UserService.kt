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

    //用户更改信息
    fun modifyInfo(userIcon:String,userName:String,userGender:String,userSign:String):Observable<BaseResp<UserInfo>>

    //忘记密码  验证
    fun forgetPwd(mobile:String,verifyCode:String):Observable<Boolean>

    //重置密码
    fun resetPwd(mobile:String,pwd:String):Observable<Boolean>
}