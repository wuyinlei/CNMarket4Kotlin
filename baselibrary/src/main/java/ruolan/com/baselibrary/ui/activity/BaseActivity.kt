package ruolan.com.baselibrary.ui.activity

import android.os.Bundle
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import ruolan.com.baselibrary.common.AppManager

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function  基类activity
 */
open class BaseActivity:RxAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppManager.instance.addActivity(this)
    }


    override fun onDestroy() {
        super.onDestroy()

        AppManager.instance.finishActivity(this)
    }

}