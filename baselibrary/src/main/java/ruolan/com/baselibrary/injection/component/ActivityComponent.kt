package ruolan.com.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component
import ruolan.com.baselibrary.injection.ActivityScope
import ruolan.com.baselibrary.injection.module.ActivityModule
import ruolan.com.baselibrary.injection.module.AppModule
import ruolan.com.baselibrary.injection.module.LifecycleProviderModule
import javax.inject.Singleton

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class,LifecycleProviderModule::class))
interface ActivityComponent {

    fun activity(): Activity

    fun lifecycleProvider(): LifecycleProvider<*>

}