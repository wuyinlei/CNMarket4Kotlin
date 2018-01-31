package ruolan.com.goodscenter.data.repository

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import ruolan.com.goodscenter.data.api.CartApi
import ruolan.com.goodscenter.data.api.CategoryApi
import ruolan.com.goodscenter.data.api.GoodsApi
import ruolan.com.goodscenter.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class CartGoodsRespository @Inject constructor() {


    fun getCartGoods(): Observable<BaseResp<MutableList<CartGoods>>> {
        return RetrofitFactory.instance.create(CartApi::class.java).getCartGoods()
    }

    fun addCartGoods(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                     goodsCount: Int, goodsSku: String):Observable<BaseResp<Int>>{
        return RetrofitFactory.instance.create(CartApi::class.java)
                .addCartGoods(AddCartGoodsReq(goodsId,goodsDesc,goodsIcon,goodsPrice,goodsCount,goodsSku))
    }

    fun delCartGoods(list: List<Int>):Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(CartApi::class.java)
                .delCartGoods(DeleteCartGoodsReq(list))
    }


}