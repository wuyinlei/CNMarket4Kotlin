package com.ruolan.factory

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function   跨模块接口调用 接口定义
 */
interface PushProvider: IProvider {

    fun getPushId():String
}