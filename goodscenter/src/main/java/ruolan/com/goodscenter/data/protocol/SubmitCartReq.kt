package ruolan.com.goodscenter.data.protocol

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
data class SubmitCartReq(val goodsList: MutableList<CartGoods>,val totalPrice: Long)