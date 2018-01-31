package ruolan.com.goodscenter.service.impl

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.goodscenter.data.protocol.Category
import ruolan.com.goodscenter.data.protocol.Goods
import ruolan.com.goodscenter.data.repository.CategoryRespository
import ruolan.com.goodscenter.data.repository.GoodsRespository
import ruolan.com.goodscenter.service.GoodsService
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class GoodsServiceImpl @Inject constructor() : GoodsService {


    override fun getGoodsDetail(goodsId: Int): Observable<BaseResp<Goods>> {
        return respository.getGoodsDetail(goodsId)
    }


    override fun getGoods(categoryId: Int, pageNo: Int): Observable<BaseResp<MutableList<Goods>>> {
        return respository.getCategory(categoryId, pageNo)
    }

    @Inject
    lateinit var respository: GoodsRespository

}