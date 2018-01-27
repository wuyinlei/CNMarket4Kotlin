package ruolan.com.goodscenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.ruolan.user.injection.component.DaggerCategoryComponent
import kotlinx.android.synthetic.main.fragment_category.*
import ruolan.com.baselibrary.ext.setVisible
import ruolan.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import ruolan.com.baselibrary.ui.fragment.BaseMvpFragment
import ruolan.com.goodscenter.R
import ruolan.com.goodscenter.data.protocol.Category
import ruolan.com.goodscenter.injection.module.CategoryModule
import ruolan.com.goodscenter.presenter.CategoryPresenter
import ruolan.com.goodscenter.presenter.view.CategoryView
import ruolan.com.goodscenter.ui.adapter.SecondCategoryAdapter
import ruolan.com.goodscenter.ui.adapter.TopCategoryAdapter

@Suppress("DEPRECATION")
/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function  分类列表
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    lateinit var  mTopCategoryAdapter:TopCategoryAdapter
    lateinit var  mSecondCategoryAdapter:SecondCategoryAdapter


    override fun injectComponent() {

        DaggerCategoryComponent.builder()
                .activityComponent(mActivityComponent)
                .categoryModule(CategoryModule())
                .build().inject(this)

        mPresenter.mView = this

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        initView()
    }

    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        mTopCategoryAdapter = TopCategoryAdapter(context)
        mTopCategoryRv.adapter = mTopCategoryAdapter
        mTopCategoryAdapter.setOnItemClickListener(object :BaseRecyclerViewAdapter.OnItemClickListener<Category>{
            override fun onItemClick(item: Category, position: Int) {

                for (category in mTopCategoryAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                mTopCategoryAdapter.notifyDataSetChanged()

                loadData(item.id)
            }
        })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        mSecondCategoryAdapter = SecondCategoryAdapter(context)
        mSecondCategoryRv.adapter = mSecondCategoryAdapter
        mSecondCategoryAdapter.setOnItemClickListener(object :BaseRecyclerViewAdapter.OnItemClickListener<Category>{
            override fun onItemClick(item: Category, position: Int) {

            }

        })

    }

    private fun loadData() {

        loadData(0)

    }

    private fun loadData(parentId: Int) {

        mPresenter.getCategory(parentId)

    }

    override fun onCategoryResult(result: MutableList<Category>) {

        if (!result.isEmpty()) {
            //当前是第一次查询 这个时候应该是查询出来了第一级分类
            // 这个时候默认的也需要把第一级分类的第一个分类的二级分类也需要查询出来
            if (result[0].parentId == 0) {
                //查询第一级分类的第一个分类的二级分类也需要查询出来
                result[0].isSelected = true
                mPresenter.getCategory(result[0].id)
                mTopCategoryAdapter.setData(result)
            } else {
                mSecondCategoryAdapter.setData(result)
                mTopCategoryIv.setVisible(true)
                mCategoryTitleTv.setVisible(true)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            //没有数据
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

    }


}