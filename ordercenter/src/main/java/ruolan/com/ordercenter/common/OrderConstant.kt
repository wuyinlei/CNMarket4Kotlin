package ruolan.com.ordercenter.common

/**
 * Created by wuyinlei on 2018/2/8.
 *
 * @function  //支付订单操作
 */

class OrderConstant {
    companion object {

        //订单状态
        const val KEY_ORDER_STATUS = "order_status"

        const val OPT_ORDER_PAY = 1
        //确认订单操作
        const val OPT_ORDER_CONFIRM = 2
        //取消订单操作
        const val OPT_ORDER_CANCEL = 3
    }
}