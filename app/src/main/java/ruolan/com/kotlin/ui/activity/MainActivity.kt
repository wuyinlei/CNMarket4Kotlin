package ruolan.com.kotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.common.AppManager
import ruolan.com.kotlin.R
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkCartBadge(20)
        mBottomNavBar.checkMsgBadge(false)

        Observable.timer(2,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({mBottomNavBar.checkMsgBadge(true)})


        Observable.timer(5,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({mBottomNavBar.checkCartBadge(0)})


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
