package ruolan.com.messagecenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
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

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */

class MessageFragment : BaseMvpFragment<MessagePresenter>(), MessageView {


    private lateinit var mAdapter: MessageAdapter

    override fun onMessageResult(result: MutableList<Message>) {

        if (!result.isEmpty()){
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            //数据为空
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
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

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /*
       加载数据
    */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getMessageList()
    }



    private fun initView() {
        mMessageRv.layoutManager = LinearLayoutManager(context)
        mAdapter = MessageAdapter(context)
        mMessageRv.adapter = mAdapter
        mMultiStateView.setStateListener(object :MultiStateView.StateListener{
            override fun onStateChanged(p0: Int) {


            }


        })
    }

    /*
       监听Fragment隐藏或显示
    */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden){

        }
    }

}
