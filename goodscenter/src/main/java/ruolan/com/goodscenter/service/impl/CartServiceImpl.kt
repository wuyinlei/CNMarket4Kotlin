package ruolan.com.goodscenter.service.impl

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.goodscenter.data.protocol.CartGoods
import ruolan.com.goodscenter.data.protocol.Category
import ruolan.com.goodscenter.data.protocol.Goods
import ruolan.com.goodscenter.data.repository.CartGoodsRespository
import ruolan.com.goodscenter.data.repository.CategoryRespository
import ruolan.com.goodscenter.data.repository.GoodsRespository
import ruolan.com.goodscenter.service.CartService
import ruolan.com.goodscenter.service.GoodsService
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class CartServiceImpl @Inject constructor() : CartService {

    override fun delCartGoods(list: List<Int>): Observable<BaseResp<String>> {
        return  respository.delCartGoods(list)

    }

    override fun addCartGoods(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<BaseResp<Int>> {
        return respository.addCartGoods(goodsId,goodsDesc,goodsIcon,goodsPrice,goodsCount,goodsSku)

    }

    override fun getCartGoodsList(): Observable<BaseResp<MutableList<CartGoods>>> {
        return respository.getCartGoods()
    }

    @Inject
    lateinit var respository: CartGoodsRespository

}