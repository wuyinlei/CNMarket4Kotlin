package ruolan.com.baselibrary.widget

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import ruolan.com.baselibrary.R

/**
 * Created by wuyinlei on 2018/1/25.
 *
 * @function
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    private val mCartBadge: TextBadgeItem
    private val mMsgBadge: ShapeBadgeItem

    init {
        //首页
        val homeItem = BottomNavigationItem(R.drawable.btn_nav_home_press,
                resources.getString(R.string.nav_bar_home))
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)

        //分类
        val categoryItem = BottomNavigationItem(R.drawable.btn_nav_category_press,
                resources.getString(R.string.nav_bar_category))
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
                .setInactiveIconResource(R.drawable.btn_nav_category_normal)

        //购物车
        val cartItem = BottomNavigationItem(R.drawable.btn_nav_cart_press,
                resources.getString(R.string.nav_bar_cart))
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)

        mCartBadge = TextBadgeItem()
        cartItem.setBadgeItem(mCartBadge)
        mCartBadge.setText("10")

        //消息
        val messageItem = BottomNavigationItem(R.drawable.btn_nav_msg_press,
                resources.getString(R.string.nav_bar_msg))
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)

        mMsgBadge = ShapeBadgeItem()
        messageItem.setBadgeItem(mMsgBadge)
        mMsgBadge.setShape(ShapeBadgeItem.SHAPE_OVAL)


        //个人
        val userItem = BottomNavigationItem(R.drawable.btn_nav_user_press,
                resources.getString(R.string.nav_bar_user))
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)

        setMode(BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)

        addItem(homeItem)
                .addItem(categoryItem)
                .addItem(cartItem)
                .addItem(messageItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise()

    }

    fun checkCartBadge(count: Int) {
        if (count == 0) {
            mCartBadge.hide()
        } else {
            mCartBadge.show()
            mCartBadge.setText("${count}")
        }
    }

    fun checkMsgBadge(isVisible: Boolean) {
        if (isVisible) {
            mMsgBadge.show()
        } else {
            mMsgBadge.hide()
        }
    }

}