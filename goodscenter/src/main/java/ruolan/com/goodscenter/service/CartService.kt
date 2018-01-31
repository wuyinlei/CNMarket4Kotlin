package ruolan.com.goodscenter.service

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import ruolan.com.goodscenter.data.api.CartApi
import ruolan.com.goodscenter.data.protocol.AddCartGoodsReq
import ruolan.com.goodscenter.data.protocol.CartGoods
import ruolan.com.goodscenter.data.protocol.DeleteCartGoodsReq
import ruolan.com.goodscenter.data.protocol.Goods
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
interface CartService {

    /**
     * 获取购物车数据
     */
    fun getCartGoods(): Observable<BaseResp<MutableList<CartGoods>>>


    /**
     * 添加购物车
     */
    fun addCartGoods(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                     goodsCount: Int, goodsSku: String):Observable<BaseResp<Int>>


    /**
     * 删除购物车数据
     */
    fun delCartGoods(list: List<Int>):Observable<BaseResp<String>>

}