package ruolan.com.goodscenter.data.repository

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import ruolan.com.goodscenter.data.api.CategoryApi
import ruolan.com.goodscenter.data.protocol.Category
import ruolan.com.goodscenter.data.protocol.GetCategoryReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class CategoryRespository @Inject constructor() {


    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }


}