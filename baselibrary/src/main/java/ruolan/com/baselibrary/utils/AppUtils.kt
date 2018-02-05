package ruolan.com.baselibrary.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import java.lang.Exception

/**
 * Created by wuyinlei on 2018/2/1.
 *
 * @function
 */
class AppUtils {

    /**
     * 判断当前应用是否是debug状态
     */
    companion object {
        fun idDebug(context: Context): Boolean {
            return try {
                val info = context.applicationInfo
                ((info.flags.and(ApplicationInfo.FLAG_DEBUGGABLE)) != 0)
            } catch (e: Exception) {
                false
            }

        }
    }

}