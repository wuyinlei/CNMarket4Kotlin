package com.ruolan.user.data.api

import com.ruolan.user.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import ruolan.com.baselibrary.data.protocol.BaseResponse
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
interface UserApi{

    @POST("kotlinserver/user/register")
    fun register(@Body req: RegisterReq):Observable<BaseResponse<String>>

}