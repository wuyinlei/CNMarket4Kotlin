package ruolan.com.baselibrary.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
class AppManager private constructor(){

    private val activityStack:Stack<Activity> = Stack()

    companion object {
        val instance:AppManager by lazy { AppManager() }
    }

    /**
     * 压栈
     */
    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    /**
     * 移除activity
     */
    fun finishActivity(activity: Activity){
        activity.finish()
        activityStack.remove(activity)
    }

    /**
     * 当前栈顶的activity
     */
    fun currentActivity():Activity{
        return activityStack.lastElement()
    }

    /**
     * 清理栈
     */
    fun finishAllActivity(){
        for (activity in activityStack)
            activity.finish()

        activityStack.clear()
    }

    /**
     * 退出应用程序
     */
    fun exitApp(context: Context){
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}