package ruolan.com.ordercenter.injection.module

import dagger.Module
import dagger.Provides
import ruolan.com.ordercenter.service.ShipAddressService
import ruolan.com.ordercenter.service.impl.ShipAddressServiceImpl

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */

@Module
class ShipAddressModule {

    @Provides
    fun providesPayService(payService: ShipAddressServiceImpl): ShipAddressService {
        return payService
    }

}