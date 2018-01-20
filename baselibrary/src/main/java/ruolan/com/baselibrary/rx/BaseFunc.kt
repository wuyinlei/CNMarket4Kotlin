package ruolan.com.baselibrary.rx

import ruolan.com.baselibrary.data.model.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class BaseFunc<T> : Func1<BaseResp<T>, Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }
}