package ruolan.com.kotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.common.AppManager
import ruolan.com.kotlin.R
import ruolan.com.kotlin.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContaier,HomeFragment())
        manager.commit()

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
