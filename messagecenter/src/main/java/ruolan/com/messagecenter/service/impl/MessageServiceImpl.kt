package ruolan.com.messagecenter.service.impl

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.messagecenter.data.protocol.Message
import ruolan.com.messagecenter.data.respository.MessageRespository
import ruolan.com.messagecenter.service.MessageService
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */
class MessageServiceImpl @Inject constructor() : MessageService {

    override fun getMessageList(): Observable<BaseResp<MutableList<Message>>> {

        return respository.getMessageList()
    }

    @Inject
    lateinit var respository: MessageRespository
}