package ruolan.com.goodscenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_goods.*
import org.jetbrains.anko.startActivity
import ruolan.com.baselibrary.ext.startLoading
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity
import ruolan.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import ruolan.com.goodscenter.R
import ruolan.com.goodscenter.common.GoodsConstant
import ruolan.com.goodscenter.data.protocol.Goods
import ruolan.com.goodscenter.injection.component.DaggerGoodsComponent
import ruolan.com.goodscenter.injection.module.GoodsModule
import ruolan.com.goodscenter.presenter.GoodsPresenter
import ruolan.com.goodscenter.presenter.view.GoodsView
import ruolan.com.goodscenter.ui.adapter.GoodsAdapter

@Suppress("DEPRECATION")
class GoodsActivity : BaseMvpActivity<GoodsPresenter>(), GoodsView, BGARefreshLayout.BGARefreshLayoutDelegate {


    private lateinit var mGoodsAdapter: GoodsAdapter
    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1

    override fun onGoodsResult(result: MutableList<Goods>) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result.size > 0) {
            mMaxPage = result[0].maxPage
            if (mCurrentPage == 1) {
                mGoodsAdapter.setData(result)
            } else {
                mGoodsAdapter.dataList.addAll(result)
                mGoodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun injectComponent() {

        DaggerGoodsComponent.builder()
                .activityComponent(mActivityComponent)
                .goodsModule(GoodsModule())
                .build().inject(this)


        mPresenter.mView = this

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initRefreshLayout()
        initView()
        loadData()
    }

    private fun initRefreshLayout() {

        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(this,2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter
        mGoodsAdapter.setOnItemClickListener(object :BaseRecyclerViewAdapter.OnItemClickListener<Goods>{
            override fun onItemClick(item: Goods, position: Int) {

                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_CATEGORY_ID to item.id)
            }

        })

    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mCurrentPage < mMaxPage) {
            mCurrentPage++
            loadData()
            true
        } else {
            false
        }

    }

    private fun loadData() {

        mMultiStateView.startLoading()
        mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 1), mCurrentPage)


    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }

}