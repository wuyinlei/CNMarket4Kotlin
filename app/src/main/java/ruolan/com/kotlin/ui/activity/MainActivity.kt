package ruolan.com.kotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.common.AppManager
import ruolan.com.kotlin.R

class MainActivity : AppCompatActivity() {

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis();
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }
}
