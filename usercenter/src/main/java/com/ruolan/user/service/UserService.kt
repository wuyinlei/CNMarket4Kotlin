package com.ruolan.user.service

import rx.Observable

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
interface UserService {

    fun register(mobile: String, vertifyCode: String, pwd: String): Observable<Boolean>

}