package ruolan.com.baselibrary.rx

import ruolan.com.baselibrary.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
open class BaseSubscriber<T>(val baseView:BaseView): Subscriber<T>() {

    override fun onNext(t: T) {
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }
}