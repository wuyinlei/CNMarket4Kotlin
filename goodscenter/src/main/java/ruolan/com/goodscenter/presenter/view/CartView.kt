package ruolan.com.goodscenter.presenter.view

import ruolan.com.baselibrary.presenter.view.BaseView
import ruolan.com.goodscenter.data.protocol.CartGoods
import ruolan.com.goodscenter.data.protocol.Goods

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
interface CartView : BaseView {

    //获取购物车列表
    fun onGetCartListResult(result: MutableList<CartGoods>?)

    //删除购物车
    fun onDeleteCartListResult(result: Boolean)

    //提交购物车
    fun onSubmitCartListResult(result: Int)

    fun onNoPermission()

}