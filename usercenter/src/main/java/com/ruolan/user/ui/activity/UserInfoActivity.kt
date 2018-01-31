package com.ruolan.user.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.qiniu.android.storage.UploadManager
import com.ruolan.user.R
import com.ruolan.user.data.model.UserInfo
import com.ruolan.user.injection.component.DaggerUserComponent
import com.ruolan.user.injection.module.UserModule
import com.ruolan.user.presenter.UserInofPresenter
import com.ruolan.user.presenter.view.UserInfoView
import com.ruolan.user.utils.putUserInfo
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.common.BaseConstants
import ruolan.com.baselibrary.ext.onClick
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity
import ruolan.com.baselibrary.utils.AppPrefsUtils
import ruolan.com.baselibrary.utils.DateUtils
import ruolan.com.baselibrary.utils.GlideUtils
import java.io.File

@Suppress("DEPRECATION")
/**
 * Created by wuyinlei on 2018/1/22.
 *
 * @function  用户信息界面
 */
class UserInfoActivity : BaseMvpActivity<UserInofPresenter>(), UserInfoView, TakePhoto.TakeResultListener {

    private val mUploadManager: UploadManager by lazy { UploadManager() }

    private var mLocalFileUrl: String? = null
    private var mRemoteFileUrl: String? = null
    private var mTempFile: File? = null


    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserMobile: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null

    private lateinit var mTakePhoto: TakePhoto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)

        initView()

        initData()
    }

    private fun initData() {

        mUserIcon = AppPrefsUtils.getString(BaseConstants.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(BaseConstants.KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.getString(BaseConstants.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(BaseConstants.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(BaseConstants.KEY_SP_USER_SIGN)

        mRemoteFileUrl = mUserIcon
        if (!TextUtils.isEmpty(mUserIcon)) {
            GlideUtils.loadUrlImage(this, mUserIcon!!, mUserIconIv)
        }

        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile

        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
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
                    mUserNameEt.text?.toString() ?: "",
                    if (mGenderMaleRb.isChecked) "0" else "1",
                    mUserSignEt.text?.toString() ?: ""
            )
        }

    }


    /**
     * 显示选择按钮
     */
    private fun showAlertView() {

        AlertView("选择图片", null, "取消", null, arrayOf("拍照", "相册"), this, AlertView.Style.ActionSheet, object : OnItemClickListener {
            override fun onItemClick(o: Any?, position: Int) {
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
                when (position) {
                    0 -> {
                        toast("拍照")
                        createTempFile()
                        mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                    }
                    1 -> {
                        mTakePhoto.onPickFromGallery()
                    }
                }
            }
        }).show()


    }


    /**
     * 获取图片上传token回调方法
     */
    override fun onGetUploadTokenResult(result: String) {
        Log.d("token",result)

        mUploadManager.put(mLocalFileUrl,null,result, { _, _, response ->
            mRemoteFileUrl = BaseConstants.IMAGE_SERVER_ADDRESS + response?.get("hash")
            Log.d("test", mRemoteFileUrl)
            GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!,mUserIconIv)
        },null)

    }


    /**
     * 更改用户信息回调方法
     */
    override fun onEditUserResult(result: UserInfo) {

        toast("修改成功")
        putUserInfo(result)
//        initData()

//        TODO()到主界面就行

    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    /**
     *  照片成功回调方法
     */
    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", "压缩版本的位置  ;" + result?.image?.compressPath + "\n" + "未压缩的位置" + result?.image?.originalPath)
        mLocalFileUrl = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

    /**
     * 拍照取消回调方法
     */
    override fun takeCancel() {

        toast("取消")
    }

    /**
     * 拍照失败回调方法
     */
    override fun takeFail(result: TResult?, msg: String?) {
        toast("失败" + msg)
    }

    /**
     * 创建临时文件
     */
    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(filesDir, tempFileName)
    }

}