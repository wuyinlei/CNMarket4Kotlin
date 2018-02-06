package ruolan.com.ordercenter.data.protocol

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
data class Order(
        val id: Int,
        val payType: Int,
        val totalPrice: Long,
        var orderStatus: Int

)
