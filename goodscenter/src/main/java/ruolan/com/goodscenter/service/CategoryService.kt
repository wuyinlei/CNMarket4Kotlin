package ruolan.com.goodscenter.service

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.goodscenter.data.protocol.Category
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
interface CategoryService {

    //获取商品类别
    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>>>

}