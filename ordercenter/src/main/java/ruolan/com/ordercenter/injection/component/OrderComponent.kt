package ruolan.com.ordercenter.injection.component

import dagger.Component
import ruolan.com.baselibrary.injection.PerComponentScope
import ruolan.com.baselibrary.injection.component.ActivityComponent
import ruolan.com.ordercenter.injection.module.OrderModule
import ruolan.com.ordercenter.ui.activity.OrderConfirmActivity

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(OrderModule::class))
interface OrderComponent {

    fun inject(activity: OrderConfirmActivity)

}