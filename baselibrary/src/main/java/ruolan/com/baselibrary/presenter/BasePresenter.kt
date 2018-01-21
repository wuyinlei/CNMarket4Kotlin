package ruolan.com.baselibrary.presenter

import android.content.Context
import com.trello.rxlifecycle.LifecycleProvider
import ruolan.com.baselibrary.presenter.view.BaseView
import ruolan.com.baselibrary.utils.NetWorkUtils
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function  presenter基类
 */
open class BasePresenter<T:BaseView> {

    lateinit var mView:T

    @Inject
    lateinit var lifecycleProvider:LifecycleProvider<*>


    @Inject
    lateinit var context:Context

    /*
       检查网络是否可用
    */
    fun checkNetWork():Boolean{
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用")
        return false
    }

}