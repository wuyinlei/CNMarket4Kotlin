package ruolan.com.ordercenter.data.protocol

/**
 * Created by wuyinlei on 2018/2/7.
 *
 * @function  更新地址
 */
data class ModifyShipAddressReq(val id:Int,val shipUserName: String,
                                val shipUserMobile: String,
                                val shipAddress: String,
                                val shipIsDefault: Int)