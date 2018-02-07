package ruolan.com.baselibrary.common

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
open class BaseConstants {

    companion object {

        //七牛服务地址
        const val IMAGE_SERVER_ADDRESS = "http://ozejm5ujq.bkt.clouddn.com/"

        //本地服务器地址
        const val SERVER_ADDRESS = "http://192.168.0.120:8080/"
        //SP表名
        const val TABLE_PREFS = "Kotlin_mall"
        //Token Key
        const val KEY_SP_TOKEN = "token"

        //用户名称
        const val KEY_SP_USER_NAME = "sp_user_name"
        //用户电话
        const val KEY_SP_USER_MOBILE = "sp_user_mobile"
        //用户头像
        const val KEY_SP_USER_ICON = "sp_user_icon"
        //用户性别
        const val KEY_SP_USER_GENDER = "sp_user_gender"
        //用户签名
        const val KEY_SP_USER_SIGN = "sp_user_sign"

        //订单ID
        const val KEY_ORDER_ID = "order_id"
        //订单价格
        const val KEY_ORDER_PRICE = "order_price"

        const val KEY_SHIP_ADDRESS = "ship_address"
    }
}