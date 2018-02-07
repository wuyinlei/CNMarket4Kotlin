package ruolan.com.ordercenter.data.protocol

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function  订单model
 */
data class Order(
        val id: Int,
        val payType: Int,
        var shipAddress: ShipAddress?,
        val totalPrice: Long,
        var orderStatus: Int,
        val orderGoodsList: MutableList<OrderGoods>
)
