package ruolan.com.ordercenter.injection.component

import dagger.Component
import ruolan.com.baselibrary.injection.PerComponentScope
import ruolan.com.baselibrary.injection.component.ActivityComponent
import ruolan.com.ordercenter.injection.module.ShipAddressModule
import ruolan.com.ordercenter.ui.activity.ShipAddressActivity
import ruolan.com.ordercenter.ui.activity.EditShipAddressActivity

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(ShipAddressModule::class))
interface ShipAddressComponent {

    fun inject(activity: ShipAddressActivity)

    fun inject(activity: EditShipAddressActivity)

}