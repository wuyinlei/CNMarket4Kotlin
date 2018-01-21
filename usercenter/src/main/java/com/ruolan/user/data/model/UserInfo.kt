package com.ruolan.user.data.model

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
data class UserInfo(
    val id: Int,
    val userName: String,
    val userPwd: String,
    val userMobile: String,
    val userIcon: String,
    val userRealName: String,
    val userIdentityCard: String,
    val userNickName: String,
    val userGender: String,
    val userBirthday: String,
    val userAddress: String,
    val userSign: String,
    val pushId: String
)