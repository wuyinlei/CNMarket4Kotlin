package ruolan.com.goodscenter.service

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.goodscenter.data.protocol.Goods
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
interface GoodsService {

    //获取商品类别
    fun getGoods(categoryId:Int,pageNo:Int): Observable<BaseResp<MutableList<Goods>>>

}