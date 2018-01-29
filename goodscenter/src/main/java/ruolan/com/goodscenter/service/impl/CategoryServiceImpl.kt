package ruolan.com.goodscenter.service.impl

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.goodscenter.data.protocol.Category
import ruolan.com.goodscenter.data.repository.CategoryRespository
import ruolan.com.goodscenter.service.CategoryService
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {

    @Inject
    lateinit var respository: CategoryRespository

    override fun getCategory(parentId:Int): Observable<BaseResp<MutableList<Category>>> {
        return respository.getCategory(parentId)
    }
}