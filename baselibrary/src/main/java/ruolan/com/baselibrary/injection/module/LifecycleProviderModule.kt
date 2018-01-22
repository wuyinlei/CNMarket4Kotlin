package ruolan.com.baselibrary.injection.module

import android.content.Context
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides
import ruolan.com.baselibrary.common.BaseApplication
import javax.inject.Singleton

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun providesLifecycleProvider(): LifecycleProvider<*> {
        return this.lifecycleProvider
    }

}