package com.ruolan.user.service

import rx.Observable

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
interface UserService {

    //用户注册
    fun register(mobile:String,pwd:String,verifyCode:String): Observable<Boolean>

}