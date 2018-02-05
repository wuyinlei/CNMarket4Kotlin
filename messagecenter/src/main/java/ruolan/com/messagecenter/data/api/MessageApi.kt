package ruolan.com.messagecenter.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.messagecenter.data.protocol.GetMessageListReq
import ruolan.com.messagecenter.data.protocol.Message
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */

interface MessageApi{

    @POST("kotlinserver/message/getList")
    fun getMessageList(@Body req: GetMessageListReq):Observable<BaseResp<MutableList<Message>>>

}
