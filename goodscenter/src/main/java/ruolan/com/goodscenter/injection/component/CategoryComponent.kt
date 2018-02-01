package ruolan.com.goodscenter.injection.component

import ruolan.com.goodscenter.injection.module.CategoryModule
import dagger.Component
import ruolan.com.baselibrary.injection.PerComponentScope
import ruolan.com.baselibrary.injection.component.ActivityComponent
import ruolan.com.goodscenter.ui.fragment.CategoryFragment

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(CategoryModule::class))
interface CategoryComponent {

    fun inject(activity: CategoryFragment)

}