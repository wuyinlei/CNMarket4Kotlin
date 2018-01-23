package com.ruolan.user.presenter

import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.presenter.view.UserInfoView
import com.ruolan.user.service.impl.UploadServiceImpl
import com.ruolan.user.service.impl.UserServiceImpl
import retrofit2.Response
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
class UserInofPresenter @Inject constructor() : BasePresenter<UserInfoView>() {


    @Inject
    lateinit var userService: UserServiceImpl

    @Inject
    lateinit var uploadService: UploadServiceImpl


    /*
     获取七牛云上传凭证
  */
    fun getUploadToken() {
        if (!checkNetWork())
            return
        mView.showLoading()

        uploadService.getUploadToken().excute(object :BaseSubscriber<String>(mView){
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        },lifecycleProvider)

    }

    /*
       编辑用户资料
    */
    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String) {
        if (!checkNetWork())
            return

        mView.showLoading()

        userService.modifyInfo(userIcon,userName,userGender,userSign)
                .excute(object :BaseSubscriber<BaseResp<UserInfo>>(mView){
                    override fun onNext(t: BaseResp<UserInfo>) {
                        mView.onEditUserResult(t.data)
                    }
                },lifecycleProvider)


    }


}