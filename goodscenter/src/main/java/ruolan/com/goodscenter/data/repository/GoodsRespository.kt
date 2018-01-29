package ruolan.com.goodscenter.data.repository

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import ruolan.com.goodscenter.data.api.CategoryApi
import ruolan.com.goodscenter.data.api.GoodsApi
import ruolan.com.goodscenter.data.protocol.Category
import ruolan.com.goodscenter.data.protocol.GetCategoryReq
import ruolan.com.goodscenter.data.protocol.GetGoodsListReq
import ruolan.com.goodscenter.data.protocol.Goods
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class GoodsRespository @Inject constructor() {


    fun getCategory(categoryId: Int,pageNo:Int): Observable<BaseResp<MutableList<Goods>>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoods(GetGoodsListReq(categoryId,pageNo))
    }


}