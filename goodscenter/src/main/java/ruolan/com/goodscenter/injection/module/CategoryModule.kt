package ruolan.com.goodscenter.injection.module

import dagger.Module
import dagger.Provides
import ruolan.com.goodscenter.service.CategoryService
import ruolan.com.goodscenter.service.impl.CategoryServiceImpl

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */

@Module
class CategoryModule {

    @Provides
    fun providesUserService(userService: CategoryServiceImpl):CategoryService{
        return userService
    }

}