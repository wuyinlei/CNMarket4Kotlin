package ruolan.com.baselibrary.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import ruolan.com.baselibrary.injection.ActivityScope

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun providesActivity(): Activity {
        return this.activity
    }

}