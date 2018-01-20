package ruolan.com.baselibrary.rx

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */

/**
 * @param status  错误码
 * @param msg 错误信息
 */
class BaseException(val status:Int,val msg:String):Throwable()