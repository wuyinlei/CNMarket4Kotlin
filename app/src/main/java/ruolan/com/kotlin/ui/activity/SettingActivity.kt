package ruolan.com.kotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ruolan.user.utils.putUserInfo
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.kotlin.R

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mUserProtocolTv.onClick {
            toast("用户协议")
        }

        mFeedBackTv.onClick {
            toast("反馈意见")
        }

        mAboutTv.onClick {
            toast("关于")
        }

        //退出登录，清空本地用户数据
        mLogoutBtn.onClick {
            putUserInfo(null)
            finish()
        }
    }
}
