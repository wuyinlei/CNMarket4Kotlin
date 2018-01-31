package ruolan.com.goodscenter.injection.component

import dagger.Component
import ruolan.com.baselibrary.injection.PerComponentScope
import ruolan.com.baselibrary.injection.component.ActivityComponent
import ruolan.com.goodscenter.injection.module.CartModule
import ruolan.com.goodscenter.ui.fragment.CartFragment

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(CartModule::class))
interface CartComponent {

    fun inject(fragment: CartFragment)

}