package ruolan.com.goodscenter.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ruolan.com.goodscenter.ui.fragment.GoodsDetailOneFragment
import ruolan.com.goodscenter.ui.fragment.GoodsDetailTwoFragment

/**
 * Created by wuyinlei on 2018/1/27.
 *
 * @function
 */
class GoodsDetailVpAdapter(fm: FragmentManager, context: Context)
    : FragmentPagerAdapter(fm) {

    private val titles = arrayOf("商品", "详情")

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            GoodsDetailOneFragment()
        } else {
            GoodsDetailTwoFragment()
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}