package ruolan.com.goodscenter.injection.module

import dagger.Module
import dagger.Provides
import ruolan.com.goodscenter.service.CartService
import ruolan.com.goodscenter.service.GoodsService
import ruolan.com.goodscenter.service.impl.CartServiceImpl
import ruolan.com.goodscenter.service.impl.GoodsServiceImpl

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */

@Module
class CartModule {

    @Provides
    fun providesUserService(cartService: CartServiceImpl): CartService {
        return cartService
    }

}