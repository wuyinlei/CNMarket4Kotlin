package ruolan.com.baselibrary.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import ruolan.com.baselibrary.injection.component.AppComponent
import ruolan.com.baselibrary.injection.component.DaggerAppComponent
import ruolan.com.baselibrary.injection.module.AppModule
import ruolan.com.baselibrary.utils.AppUtils

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
open class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection()

        context = this

        //判断当只有当前app处于debug的时候才去打印日志
        if (AppUtils.idDebug(this)) {
            //ARouter初始化
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    private fun initAppInjection() {

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this))
                .build()

    }

    /*
        全局伴生对象
     */
    companion object {
        lateinit var context: Context
    }
}