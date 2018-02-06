package ruolan.com.ordercenter.injection.module

import dagger.Module
import dagger.Provides
import ruolan.com.ordercenter.service.OrderService
import ruolan.com.ordercenter.service.impl.OrderServiceImpl

/**
 * Created by wuyinlei on 2018/1/21.
 *
 *
 * @function
 */

@Module
class OrderModule {

    @Provides
    fun providesPayService(payService: OrderServiceImpl): OrderService {
        return payService
    }

}