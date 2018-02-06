package ruolan.com.paysdk.injection.component

import dagger.Component
import ruolan.com.baselibrary.injection.PerComponentScope
import ruolan.com.baselibrary.injection.component.ActivityComponent
import ruolan.com.paysdk.injection.module.PayModule
import ruolan.com.paysdk.ui.activity.PayResultActivity

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(PayModule::class))
interface PayComponent {

    fun inject(activity: PayResultActivity)

}