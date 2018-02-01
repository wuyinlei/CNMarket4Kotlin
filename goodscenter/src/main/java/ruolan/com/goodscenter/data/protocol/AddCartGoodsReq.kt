package ruolan.com.goodscenter.data.protocol

/**
 * Created by wuyinlei on 2018/1/31.
 */
data class AddCartGoodsReq(
        val goodsId: Int, //商品ID
        val goodsDesc: String, //商品描述
        val goodsIcon: String, //商品图标
        val goodsPrice: Long, //商品价格
        val goodsCount: Int, //商品数量
        val goodsSku: String //商品SKU
)
