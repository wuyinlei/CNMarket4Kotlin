package ruolan.com.goodscenter.injection.component

import dagger.Component
import ruolan.com.baselibrary.injection.PerComponentScope
import ruolan.com.baselibrary.injection.component.ActivityComponent
import ruolan.com.goodscenter.injection.module.CategoryModule
import ruolan.com.goodscenter.injection.module.GoodsModule
import ruolan.com.goodscenter.ui.activity.GoodsActivity
import ruolan.com.goodscenter.ui.fragment.CategoryFragment

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(GoodsModule::class))
interface CoodsComponent {

    fun inject(activity: GoodsActivity)

}