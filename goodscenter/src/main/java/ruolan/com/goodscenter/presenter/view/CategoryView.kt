package ruolan.com.goodscenter.presenter.view

import ruolan.com.baselibrary.presenter.view.BaseView
import ruolan.com.goodscenter.data.protocol.Category

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
interface CategoryView : BaseView {

    fun onCategoryResult(result: List<Category>)

}