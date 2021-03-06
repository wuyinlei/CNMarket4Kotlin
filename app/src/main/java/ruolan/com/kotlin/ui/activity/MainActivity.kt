package ruolan.com.kotlin.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.ruolan.factory.event.MessageBadgeEvent
import com.ruolan.factory.router.RouterPath
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.common.AppManager
import ruolan.com.baselibrary.ui.activity.BaseActivity
import ruolan.com.goodscenter.ui.fragment.CartFragment
import ruolan.com.goodscenter.ui.fragment.CategoryFragment
import ruolan.com.kotlin.R
import ruolan.com.kotlin.ui.fragment.HomeFragment
import ruolan.com.kotlin.ui.fragment.MineFragment
import ruolan.com.messagecenter.ui.fragment.MessageFragment
import java.util.*

@Route(path = RouterPath.MainCenter.MAIN_PATH)
class MainActivity : BaseActivity() {

    private var pressTime: Long = 0

    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { CartFragment() }
    private val mMsgFragment by lazy { MessageFragment() }

    private val mMineFragment by lazy { MineFragment() }

    private val mStack = Stack<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initBottomNav()

        initObserve()

    }

    private fun initObserve() {

        Bus.observe<MessageBadgeEvent>()
                .subscribe {
                    t: MessageBadgeEvent ->
                    run {
                        mBottomNavBar.checkMsgBadge(t.isVisible)
                    }
                }.registerInBus(this)


    }

    private fun initBottomNav() {


        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {

            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })

        mBottomNavBar.checkMsgBadge(false)
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack) {
            manager.hide(fragment)
        }

        manager.show(mStack[position])
        manager.commit()
    }

    private fun initFragment() {

        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMineFragment)

        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContaier, mHomeFragment)
        manager.add(R.id.mContaier, mCategoryFragment)
        manager.add(R.id.mContaier, mCartFragment)
        manager.add(R.id.mContaier, mMsgFragment)
        manager.add(R.id.mContaier, mMineFragment)
        manager.commit()

        //将第一个fragment进行显示
        changeFragment(0)

    }

    /*
       取消Bus事件监听
    */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
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
