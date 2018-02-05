package ruolan.com.messagecenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.kennyc.view.MultiStateView
import com.ruolan.factory.router.RouterPath
import kotlinx.android.synthetic.main.fragment_message.*
import ruolan.com.baselibrary.ext.startLoading
import ruolan.com.baselibrary.ui.fragment.BaseMvpFragment
import ruolan.com.messagecenter.R
import ruolan.com.messagecenter.data.protocol.Message
import ruolan.com.messagecenter.injection.component.DaggerMessageComponent
import ruolan.com.messagecenter.injection.module.MessageModule
import ruolan.com.messagecenter.presenter.MessagePresenter
import ruolan.com.messagecenter.presenter.view.MessageView
import ruolan.com.messagecenter.ui.adapter.MessageAdapter

@Suppress("DEPRECATION")
/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */

class MessageFragment : BaseMvpFragment<MessagePresenter>(), MessageView, BGARefreshLayout.BGARefreshLayoutDelegate {


    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1
    private var mLoadMore: Boolean = true


    private lateinit var mAdapter: MessageAdapter

    override fun onMessageResult(result: MutableList<Message>) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (!result.isEmpty()) {
            if (mCurrentPage == 1) {
                mAdapter.setData(result)
            } else {
                mAdapter.dataList.addAll(result)
                mAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT

            mCurrentPage++
            if (result.size < 10)
                mLoadMore = false

        } else {
            if (mCurrentPage > 1) {
                mLoadMore = false
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            } else {
                //数据为空
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
            }
        }

    }

    override fun injectComponent() {
        DaggerMessageComponent.builder()
                .activityComponent(mActivityComponent)
                .messageModule(MessageModule())
                .build()
                .inject(this)

        mPresenter.mView = this

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        initRefreshLayout()

    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(activity, true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
        mRefreshLayout.setPullDownRefreshEnable(false)
    }

    override fun onStart() {
        super.onStart()
        loadData(mCurrentPage)
    }

    /*
       加载数据
    */
    private fun loadData(pageNo: Int) {
//        mMultiStateView.startLoading()
        mPresenter.getMessageList(pageNo)
    }


    private fun initView() {
        mMessageRv.layoutManager = LinearLayoutManager(context)
        mAdapter = MessageAdapter(context)
        mMessageRv.adapter = mAdapter
        mMultiStateView.setStateListener { }
    }

    /*
       监听Fragment隐藏或显示
    */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {

        }
    }

    override fun onNoPermission() {
        mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR
        mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR)?.setOnClickListener({
            //跳转到登录界面
            ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
        })
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mLoadMore) {
            loadData(mCurrentPage)
            true
        } else {
            false
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        //刷新
    }

}
