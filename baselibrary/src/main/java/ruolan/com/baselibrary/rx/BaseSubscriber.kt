package ruolan.com.baselibrary.rx

import rx.Subscriber

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
open class BaseSubscriber<T> : Subscriber<T>() {
    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }

    override fun onNext(t: T) {
    }
}