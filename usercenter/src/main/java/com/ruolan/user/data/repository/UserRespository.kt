package com.ruolan.user.data.repository

import com.ruolan.user.data.api.UserApi
import com.ruolan.user.data.protocol.RegisterReq
import ruolan.com.baselibrary.data.net.RetrofitFactory
import ruolan.com.baselibrary.data.protocol.BaseResponse
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
class UserRespository{
    fun register(mobile: String, vertifyCode: String, pwd: String): Observable<BaseResponse<String>>{
       return RetrofitFactory.instance.create(UserApi::class.java).register(RegisterReq(mobile,vertifyCode,pwd))
    }
}