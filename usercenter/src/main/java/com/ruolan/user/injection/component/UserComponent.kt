package com.ruolan.user.injection.component

import com.ruolan.user.injection.module.UserModule
import com.ruolan.user.ui.activity.RegisterActivity
import dagger.Component

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@Component(modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(activity:RegisterActivity)

}