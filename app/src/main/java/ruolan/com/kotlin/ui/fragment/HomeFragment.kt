package ruolan.com.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ruolan.com.kotlin.common.HOME_BANNER_FOUR
import ruolan.com.kotlin.common.HOME_BANNER_ONE
import ruolan.com.kotlin.common.HOME_BANNER_THREE
import ruolan.com.kotlin.common.HOME_BANNER_TWO
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import org.jetbrains.anko.find
import ruolan.com.baselibrary.ui.fragment.BaseFragment
import ruolan.com.baselibrary.widget.BannerImageLoader
import ruolan.com.kotlin.R

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class HomeFragment : BaseFragment() {

    private lateinit var mHomeBanner:Banner

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_home, container, false)
        initView(rootView)
        initBanner(rootView!!)
        return rootView
    }

    private fun initBanner(rootView:View) {
        mHomeBanner = rootView.find(R.id.mHomeBanner)
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(3000)
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        mHomeBanner.start()
    }

    private fun initView(rootView: View?) {


    }

}
