package ruolan.com.goodscenter.presenter

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.goodscenter.data.protocol.Category
import ruolan.com.goodscenter.presenter.view.CategoryView
import ruolan.com.goodscenter.service.impl.CategoryServiceImpl
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {


    @Inject
    lateinit var categoryService : CategoryServiceImpl

    fun getCategory(parentId:Int) {

        categoryService.getCategory(parentId)
                .excute(object :BaseSubscriber<BaseResp<List<Category>>>(mView){
                    override fun onNext(t: BaseResp<List<Category>>) {
                        mView.onCategoryResult(t.data)
                    }
                },lifecycleProvider)

    }
}