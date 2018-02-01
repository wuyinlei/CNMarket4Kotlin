package ruolan.com.goodscenter.ui.activity

import android.os.Bundle
import ruolan.com.baselibrary.ui.activity.BaseActivity
import ruolan.com.goodscenter.R
import ruolan.com.goodscenter.ui.fragment.CartFragment

class CartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)
    }


}
