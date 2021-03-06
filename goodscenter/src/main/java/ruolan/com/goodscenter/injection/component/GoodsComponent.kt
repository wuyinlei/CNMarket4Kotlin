package ruolan.com.goodscenter.injection.component

import dagger.Component
import ruolan.com.baselibrary.injection.PerComponentScope
import ruolan.com.baselibrary.injection.component.ActivityComponent
import ruolan.com.goodscenter.injection.module.CartModule
import ruolan.com.goodscenter.injection.module.GoodsModule
import ruolan.com.goodscenter.ui.activity.GoodsActivity
import ruolan.com.goodscenter.ui.fragment.GoodsDetailOneFragment

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(GoodsModule::class,CartModule::class))
interface GoodsComponent {

    fun inject(activity: GoodsActivity)

    fun inject(fragment: GoodsDetailOneFragment)

}