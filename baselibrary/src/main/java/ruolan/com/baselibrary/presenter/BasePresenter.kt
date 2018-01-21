package ruolan.com.baselibrary.presenter

import com.trello.rxlifecycle.LifecycleProvider
import ruolan.com.baselibrary.presenter.view.BaseView
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

}