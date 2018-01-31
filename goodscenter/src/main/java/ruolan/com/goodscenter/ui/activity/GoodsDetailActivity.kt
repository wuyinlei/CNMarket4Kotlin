package ruolan.com.goodscenter.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentActivity
import com.eightbitlab.rxbus.Bus
import com.ruolan.factory.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ui.activity.BaseActivity
import ruolan.com.goodscenter.R
import ruolan.com.goodscenter.event.AddCartEvent
import ruolan.com.goodscenter.ui.adapter.GoodsDetailVpAdapter

class GoodsDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        initView()
    }

    private fun initView() {

        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager)
        //TabLayout关联ViewPager
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)


        //通知附属的fragment进行添加购物车
        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }

        mEnterCartTv.onClick {
            startActivity<CartActivity>()
        }

        mLeftIv.onClick {
            finish()
        }

    }
}