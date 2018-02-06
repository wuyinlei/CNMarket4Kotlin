package ruolan.com.paysdk.injection.module

import dagger.Module
import dagger.Provides
import ruolan.com.paysdk.service.PayService
import ruolan.com.paysdk.service.impl.PayServiceImpl

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */

@Module
class PayModule {

    @Provides
    fun providesPayService(payService: PayServiceImpl): PayService {
        return payService
    }

}