package ruolan.com.baselibrary.common

import android.app.Application
import android.content.Context
import ruolan.com.baselibrary.injection.component.AppComponent
import ruolan.com.baselibrary.injection.component.DaggerAppComponent
import ruolan.com.baselibrary.injection.module.AppModule

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
open class BaseApplication:Application(){

    lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection()

        context = this
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