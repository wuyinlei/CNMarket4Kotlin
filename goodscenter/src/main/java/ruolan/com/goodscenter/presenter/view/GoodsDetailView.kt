package ruolan.com.goodscenter.presenter.view

import ruolan.com.baselibrary.presenter.view.BaseView
import ruolan.com.goodscenter.data.protocol.Goods

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */
interface GoodsDetailView:BaseView {

    //获取商品详情
    fun onGetGoodsDetailResult(result: Goods)

    //加入购物车
    fun onAddCartResult(result: Int)

}