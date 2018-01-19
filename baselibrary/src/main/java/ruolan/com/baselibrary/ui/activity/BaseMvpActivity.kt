package ruolan.com.baselibrary.ui.activity

import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.presenter.view.BaseView

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function  mvp activity基类
 *
 */
open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    override fun hideLoading() {
    }

    override fun onError() {
    }

    override fun showLoading() {
    }


    lateinit var mPresenter: T
}