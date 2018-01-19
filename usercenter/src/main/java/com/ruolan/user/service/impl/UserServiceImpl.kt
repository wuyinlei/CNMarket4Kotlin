package com.ruolan.user.service.impl

import com.ruolan.user.data.repository.UserRespository
import com.ruolan.user.service.UserService
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
class UserServiceImpl : UserService {

    override fun register(mobile: String, vertifyCode: String, pwd: String): Observable<Boolean> {

        val respository = UserRespository()
        return respository.register(mobile, vertifyCode, pwd)
                .flatMap { t ->
                    if (t.status != 0) {
                        //在这里处理登录不成功的情况
                    }
                    Observable.just(true)
                }

    }
}
