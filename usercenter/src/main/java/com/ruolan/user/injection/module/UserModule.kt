package com.ruolan.user.injection.module

import com.ruolan.user.service.UserService
import com.ruolan.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */

@Module
class UserModule {

    @Provides
    fun providesUserService(userService: UserServiceImpl):UserService{
        return userService
    }

}