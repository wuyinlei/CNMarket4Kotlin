package com.ruolan.user.service.impl

import com.ruolan.user.data.repository.UserRespository
import com.ruolan.user.service.UserService
import ruolan.com.baselibrary.ext.convertBoolean
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class UserServiceImpl:UserService {

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        val respository = UserRespository()
        return respository.register(mobile,pwd,verifyCode).convertBoolean()
    }
}