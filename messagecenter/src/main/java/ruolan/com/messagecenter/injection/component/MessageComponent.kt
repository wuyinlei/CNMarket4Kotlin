package ruolan.com.messagecenter.injection.component

import dagger.Component
import ruolan.com.baselibrary.injection.PerComponentScope
import ruolan.com.baselibrary.injection.component.ActivityComponent
import ruolan.com.messagecenter.injection.module.MessageModule
import ruolan.com.messagecenter.ui.fragment.MessageFragment

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(MessageModule::class))
interface MessageComponent {

    fun inject(fragment:MessageFragment)

}