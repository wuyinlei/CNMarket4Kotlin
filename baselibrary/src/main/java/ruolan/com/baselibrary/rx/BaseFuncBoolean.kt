package ruolan.com.baselibrary.rx

import ruolan.com.baselibrary.common.ResultCode
import ruolan.com.baselibrary.data.model.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class BaseFuncBoolean<T> : Func1<BaseResp<T>, Observable<Boolean>> {
    override fun call(t: BaseResp<T>): Observable<Boolean> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }
}