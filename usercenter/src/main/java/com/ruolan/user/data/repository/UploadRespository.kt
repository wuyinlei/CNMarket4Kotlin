package com.ruolan.user.data.repository

import com.ruolan.user.data.api.UploadApi
import com.ruolan.user.data.api.UserApi
import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.data.protocol.*
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class UploadRespository @Inject constructor(){


    fun upload(): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()
    }


}