package ruolan.com.ordercenter.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ruolan.com.ordercenter.common.OrderConstant
import ruolan.com.ordercenter.ui.fragment.OrderFragment

/**
 * Created by wuyinlei on 2018/2/8.
 *
 * @function
 */
class OrderVpAdapter(fm: FragmentManager, context: Context)
    : FragmentPagerAdapter(fm) {

    private val titles = arrayOf("全部","待付款","待收货","已完成","已取消")

    override fun getItem(position: Int): Fragment {
        val fragment = OrderFragment()
        val bundle = Bundle()
        bundle.putInt(OrderConstant.KEY_ORDER_STATUS,position)
        fragment.arguments = bundle
        return fragment

    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}
