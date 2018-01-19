package ruolan.com.baselibrary.widget

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by wuyinlei on 2018/1/19.
 *
 *
 */
open class DefaultTextWatcher:TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}