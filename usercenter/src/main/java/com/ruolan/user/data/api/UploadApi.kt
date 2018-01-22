package com.ruolan.user.data.api

import retrofit2.http.POST
import ruolan.com.baselibrary.data.model.BaseResp
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
interface UploadApi {

    /*
       获取七牛云上传凭证
    */
    @POST("kotlinserver/user/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>

}