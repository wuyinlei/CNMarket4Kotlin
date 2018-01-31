package ruolan.com.goodscenter

import android.widget.EditText
import org.jetbrains.anko.find
import ren.qinc.numberbutton.NumberButton
import ren.qinc.numberbutton.R


/**
 * Created by wuyinlei on 2018/1/31.
 *
 * @function
 */
/*
    三方控件扩展
 */
fun NumberButton.getEditText(): EditText {
    return find(R.id.text_count)
}