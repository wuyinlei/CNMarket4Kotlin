package com.ruolan.user.injection.component

import com.ruolan.user.injection.module.UserModule
import com.ruolan.user.ui.activity.RegisterActivity
import dagger.Component
import ruolan.com.baselibrary.injection.PerComponentScope
import ruolan.com.baselibrary.injection.component.ActivityComponent

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(activity:RegisterActivity)

}