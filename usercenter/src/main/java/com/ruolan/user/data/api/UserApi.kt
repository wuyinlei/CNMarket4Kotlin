package com.ruolan.user.data.api

import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import ruolan.com.baselibrary.data.model.BaseResp
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
interface UserApi {

    /**
     * 用户注册
     */
    @POST("kotlinserver/user/register")
    fun register(@Body reg: RegisterReq): Observable<BaseResp<UserInfo>>

    @POST("kotlinserver/user/login")
    fun login(@Body log: LoginReq): Observable<BaseResp<UserInfo>>

    /*
    编辑用户资料
 */
    @POST("kotlinserver/user/update")
    fun editUser(@Body req: EditUserReq): Observable<BaseResp<UserInfo>>

    /**
     * 忘记密码校验
     */
    @POST("kotlinserver/user/forgetPwd")
    fun forgetPwd(@Body req: ForgetUserReq): Observable<BaseResp<String>>

    @POST("kotlinserver/user/reset")
    fun resetPwd(@Body req: ResetPwdReq): Observable<BaseResp<String>>

}