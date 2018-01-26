package com.ruolan.user.service.impl

import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.data.repository.UserRespository
import com.ruolan.user.service.UserService
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.convertBoolean
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var respository: UserRespository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {

        return respository.register(mobile, pwd, verifyCode).convertBoolean()
    }

    override fun login(mobile: String, password: String, pushId: String): Observable<BaseResp<UserInfo>> {
        return respository.login(mobile,password,pushId)
    }


    override fun modifyInfo(userIcon: String, userName: String, userGender: String, userSign: String): Observable<BaseResp<UserInfo>> {
        return respository.editUser(userIcon,userName,userGender,userSign)
    }

    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return respository.forgetPwd(mobile,verifyCode).convertBoolean()
    }

    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return respository.resetPwd(mobile,pwd).convertBoolean()
    }


}