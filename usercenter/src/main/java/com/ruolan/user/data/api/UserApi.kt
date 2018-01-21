package com.ruolan.user.data.api

import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.data.protocol.RegisterReq
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

}