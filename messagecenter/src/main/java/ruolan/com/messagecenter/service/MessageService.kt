package ruolan.com.messagecenter.service

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.messagecenter.data.protocol.Message
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */

interface MessageService{

    fun getMessageList():Observable<BaseResp<MutableList<Message>>>

}
