package ruolan.com.kotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ruolan.factory.common.isLogined
import com.ruolan.user.ui.activity.LoginActivity
import org.jetbrains.anko.startActivity
import ruolan.com.kotlin.R
import rx.Observable
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (isLogined()){
            Observable.timer(2000,TimeUnit.MILLISECONDS)
                    .subscribe {
                        startActivity<MainActivity>()
                        finish()
                    }
        } else{
            Observable.timer(2000,TimeUnit.MILLISECONDS)
                    .subscribe {
                        startActivity<LoginActivity>()
                        finish()
                    }
        }
    }
}
