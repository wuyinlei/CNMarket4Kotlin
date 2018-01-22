package ruolan.com.baselibrary.presenter.view

/**
 * Created by wuyinlei on 2018/1/19.
 */
interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun onError(message:String)

}