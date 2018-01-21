package ruolan.com.baselibrary.injection.module

import android.content.Context
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
class AppModule(private val context: BaseApplication) {

    @Singleton
    @Provides
    fun providesContext(): Context {
        return this.context
    }

}