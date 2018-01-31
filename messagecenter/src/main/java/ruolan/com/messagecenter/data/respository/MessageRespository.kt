package ruolan.com.messagecenter.data.respository

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.data.net.RetrofitFactory
import ruolan.com.messagecenter.data.api.MessageApi
import ruolan.com.messagecenter.data.protocol.Message
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */
class MessageRespository @Inject constructor() {

    fun getMessageList(): Observable<BaseResp<MutableList<Message>>> {
        return RetrofitFactory.instance
                .create(MessageApi::class.java)
                .getMessageList()
    }

}