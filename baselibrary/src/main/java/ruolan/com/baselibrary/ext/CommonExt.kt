package ruolan.com.baselibrary.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.rx.BaseFuncBoolean
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.baselibrary.widget.DefaultTextWatcher
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


//Kotlin通用扩展


/*
    扩展Observable执行
 */
fun <T> Observable<T>.excute(subscriber: BaseSubscriber<T>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}


/*
    扩展Boolean类型数据转换
 */
fun <T> Observable<BaseResp<T>>.convertBoolean():Observable<Boolean>{
    return this.flatMap(BaseFuncBoolean())
}


/*
    扩展点击事件
 */
fun View.onClick(listener:View.OnClickListener):View{
    setOnClickListener(listener)
    return this
}

/*
    扩展Button可用性
 */
fun Button.enable(et: EditText, method: () -> Boolean){
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}


/*
    扩展点击事件，参数为方法
 */
fun View.onClick(method:() -> Unit):View {
    setOnClickListener { method() }
    return this
}
/*
    扩展视图可见性
 */
fun View.setVisible(visible:Boolean){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
