package ruolan.com.messagecenter.presenter.view

import ruolan.com.baselibrary.presenter.view.BaseView
import ruolan.com.messagecenter.data.protocol.Message

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */
interface MessageView : BaseView {

    fun onMessageResult(result: MutableList<Message>)

    fun onNoPermission()
}