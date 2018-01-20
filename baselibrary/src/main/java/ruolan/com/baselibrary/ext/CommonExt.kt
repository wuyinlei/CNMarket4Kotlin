package ruolan.com.baselibrary.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import ruolan.com.baselibrary.widget.DefaultTextWatcher


//Kotlin通用扩展



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
