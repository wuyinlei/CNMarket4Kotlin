package com.ruolan.user.ui.activity

import android.os.Bundle
import android.text.TextUtils
import com.ruolan.user.R
import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.injection.component.DaggerUserComponent
import com.ruolan.user.injection.module.UserModule
import com.ruolan.user.presenter.UserInofPresenter
import com.ruolan.user.presenter.view.UserInfoView
import com.ruolan.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.common.Constants
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity
import ruolan.com.baselibrary.utils.AppPrefsUtils
import ruolan.com.baselibrary.utils.GlideUtils

/**
 * Created by wuyinlei on 2018/1/22.
 *
 * @function  用户信息界面
 */
class UserInfoActivity : BaseMvpActivity<UserInofPresenter>(), UserInfoView {


    private var mLocalFileUrl:String? = null
    private var mRemoteFileUrl:String? = null

    private var mUserIcon: String? = null
    private var mUserName:String?=null
    private var mUserMobile:String? = null
    private var mUserGender:String? = null
    private var mUserSign:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()

        initData()
    }

    private fun initData() {

        mUserIcon = AppPrefsUtils.getString(Constants.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(Constants.KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.getString(Constants.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(Constants.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(Constants.KEY_SP_USER_SIGN)

        mRemoteFileUrl = mUserIcon
        if (!TextUtils.isEmpty(mUserIcon)){
            GlideUtils.loadUrlImage(this,mUserIcon!!,mUserIconIv)
        }

        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile

        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        }
        else {
            mGenderFemaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)

    }

    private fun initView() {
        mUserIconView.onClick {
            showAlertView()
        }

        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(mRemoteFileUrl!!,
                    mUserNameEt.text?.toString()?:"",
                    if(mGenderMaleRb.isChecked) "0" else "1",
                    mUserSignEt.text?.toString()?:""
            )
        }

    }


    /**
     * 显示选择按钮
     */
    private fun showAlertView() {




    }

    override fun onGetUploadTokenResult(result: String) {

    }

    override fun onEditUserResult(result: UserInfo) {

        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
        initData()

    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }
}