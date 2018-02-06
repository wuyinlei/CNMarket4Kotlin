package ruolan.com.paysdk.service

import rx.Observable

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
interface PayService {

    /*
  获取支付宝支付签名
*/
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<String>

    /*
    刷新订单状态已支付
 */
    fun payOrder(orderId: Int): Observable<Boolean>

}