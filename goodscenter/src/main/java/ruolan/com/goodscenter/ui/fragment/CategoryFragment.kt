package ruolan.com.goodscenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruolan.user.injection.component.DaggerCategoryComponent
import kotlinx.android.synthetic.main.fragment_cart.*
import ruolan.com.baselibrary.ext.setVisible
import ruolan.com.baselibrary.ui.fragment.BaseMvpFragment
import ruolan.com.goodscenter.R
import ruolan.com.goodscenter.data.protocol.Category
import ruolan.com.goodscenter.injection.module.CategoryModule
import ruolan.com.goodscenter.presenter.CategoryPresenter
import ruolan.com.goodscenter.presenter.view.CategoryView

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function  分类列表
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {


    override fun injectComponent() {

        DaggerCategoryComponent.builder()
                .activityComponent(mActivityComponent)
                .categoryModule(CategoryModule())
                .build().inject(this)

        mPresenter.mView = this

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {


        loadData(0)

    }

    private fun loadData(parentId: Int) {

        mPresenter.getCategory(parentId)

    }

    override fun onCategoryResult(result: List<Category>) {


        if (!result.isEmpty()) {
            //当前是第一次查询 这个时候应该是查询出来了第一级分类
            // 这个时候默认的也需要把第一级分类的第一个分类的二级分类也需要查询出来
            if (result[0].parentId == 0) {
                //查询第一级分类的第一个分类的二级分类也需要查询出来
                mPresenter.getCategory(result[0].id)
            } else {

            }
        } else {

        }

    }

    /*
       设置Back是否可见
    */
    fun setBackVisible(isVisible: Boolean) {
        mHeaderBar.getLeftView().setVisible(isVisible)
    }

}