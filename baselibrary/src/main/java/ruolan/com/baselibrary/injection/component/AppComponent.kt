package ruolan.com.baselibrary.injection.component

import android.content.Context
import dagger.Component
import ruolan.com.baselibrary.injection.module.AppModule
import javax.inject.Singleton

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun context():Context

}