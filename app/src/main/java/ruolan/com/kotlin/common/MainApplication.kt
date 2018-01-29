package ruolan.com.kotlin.common

import cn.jpush.android.api.JPushInterface
import ruolan.com.baselibrary.common.BaseApplication

/**
 * Created by wuyinlei on 2018/1/29.
 *
 * @function
 */
class MainApplication:BaseApplication() {


    override fun onCreate() {
        super.onCreate()

        //极光推送初始化
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}