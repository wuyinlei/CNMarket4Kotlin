package com.ruolan.user.data.protocol

/**
 * Created by wuyinlei on 2018/1/22.
 *
 * @function
 */
data class ResetPwdReq(var mobile: String,
                       var newPassword: String)