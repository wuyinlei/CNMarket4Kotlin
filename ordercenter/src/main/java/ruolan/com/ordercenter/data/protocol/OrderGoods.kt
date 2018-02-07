package ruolan.com.ordercenter.data.protocol

/**
 * Created by wuyinlei on 2018/2/7.
 *
 * @function 订单中的商品
 */
data class OrderGoods(
        val id: Int,
        var goodsId: Int,
        val goodsDesc: String,
        val goodsIcon: String,
        val goodsPrice: Long,
        val goodsCount: Int,
        val goodsSku: String,
        val orderId: Int
)
