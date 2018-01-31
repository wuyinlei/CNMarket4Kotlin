package ruolan.com.messagecenter.presenter

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.messagecenter.data.protocol.Message
import ruolan.com.messagecenter.presenter.view.MessageView
import ruolan.com.messagecenter.service.impl.MessageServiceImpl
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */
class MessagePresenter @Inject constructor() : BasePresenter<MessageView>() {

    @Inject
     lateinit var messageService: MessageServiceImpl

    fun getMessageList() {
        messageService
                .getMessageList()
                .excute(object : BaseSubscriber<BaseResp<MutableList<Message>>>(mView) {
                    override fun onNext(t: BaseResp<MutableList<Message>>) {
                        mView.onMessageResult(t.data)
                    }
                }, lifecycleProvider)
    }
}