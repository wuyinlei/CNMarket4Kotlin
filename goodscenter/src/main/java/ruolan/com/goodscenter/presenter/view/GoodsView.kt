package ruolan.com.goodscenter.presenter.view

import ruolan.com.baselibrary.presenter.view.BaseView
import ruolan.com.goodscenter.data.protocol.Goods

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
interface GoodsView : BaseView {

    fun onGoodsResult(result: MutableList<Goods>)

}