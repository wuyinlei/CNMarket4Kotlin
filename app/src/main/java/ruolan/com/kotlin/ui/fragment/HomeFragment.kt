package ruolan.com.kotlin.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import ruolan.com.baselibrary.ui.fragment.BaseFragment
import ruolan.com.baselibrary.widget.BannerImageLoader
import ruolan.com.kotlin.R
import ruolan.com.kotlin.common.*
import ruolan.com.kotlin.ui.adapter.HomeDiscountAdapter
import ruolan.com.kotlin.ui.adapter.TopicAdapter

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container,savedInstanceState)
        return inflater.inflate(R.layout.fragment_home,null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initBanner()
        initDiscount()
        initFlipper()
        initTopic()
    }


    /**
     * 初始化banner
     */
    private fun initBanner() {
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(3000)
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        mHomeBanner.start()
    }

    /**
     * 初始化公告
     */
    private fun initFlipper(){
        mNewsFlipperView.setData(arrayOf(HOME_FLIPPER_ONE, HOME_FLIPPER_TWO))

    }

    private fun initView() {
    }

    /*
      初始化折扣
   */
    private fun initDiscount(){
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manager

        val discountAdapter  = HomeDiscountAdapter(activity)
        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
    }

    /*
      初始化主题
   */
    private fun initTopic(){
        //话题
        mTopicPager.adapter = TopicAdapter(context, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager).scale(0.3f).pagerMargin(-30.0f).spaceSize(0.0f).build()
    }


}
