package com.ruolan.user.service

import rx.Observable

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
interface UploadService {

    fun getUploadToken(): Observable<String>

}