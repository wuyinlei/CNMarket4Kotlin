package ruolan.com.baselibrary.data.protocol

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
class BaseResponse<T>(val status:Int,val message:String,val data:T)