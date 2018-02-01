package ruolan.com.goodscenter.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.goodscenter.data.protocol.Category
import ruolan.com.goodscenter.data.protocol.GetCategoryReq
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
interface CategoryApi {

    @POST("kotlinserver/category/getlist")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>>>


}