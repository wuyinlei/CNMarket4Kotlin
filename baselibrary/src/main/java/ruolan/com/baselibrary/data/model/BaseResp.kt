package ruolan.com.baselibrary.data.model

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
/**
 * @param status  响应码
 * @param message 响应信息
 * @param data 响应数据
 */
data class BaseResp<out T>(val status: Int, val message: String, val data: T)