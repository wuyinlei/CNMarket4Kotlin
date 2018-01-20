package ruolan.com.baselibrary.rx

import ruolan.com.baselibrary.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class BaseSubscriber<T>(val mView:BaseView): Subscriber<T>() {

    override fun onNext(t: T) {
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }
}