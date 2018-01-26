package ruolan.com.messagecenter.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.ViewFlipper
import kotlinx.android.synthetic.main.layout_news_flipper.view.*
import org.jetbrains.anko.dimen
import org.jetbrains.anko.px2sp
import ruolan.com.messagecenter.R

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class NewsFlipperView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val mFilpperView: ViewFlipper

    init {
        val rootView = View.inflate(context, R.layout.layout_news_flipper, null)
        mFilpperView = rootView.findViewById(R.id.mFlipperView)
        mFilpperView.setInAnimation(context, R.anim.news_bottom_in)
        mFilpperView.setOutAnimation(context, R.anim.news_bottom_out)
        addView(rootView)
    }

    /*
      构建公告
   */
    private fun buildNewsView(text: String): View {
        val textView = TextView(context)
        textView.text = text
        textView.setSingleLine(true)
        textView.ellipsize = TextUtils.TruncateAt.END
        textView.setPadding(0,0,20,0)
        textView.textSize = px2sp(dimen(R.dimen.text_small_size))
        textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        return textView
    }

    /*
        设置公告数据
     */
    fun setData(data: Array<String>) {
        for (text in data) {
            mFlipperView.addView(buildNewsView(text))
        }
        mFlipperView.startFlipping()
    }
}