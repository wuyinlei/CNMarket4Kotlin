package ruolan.com.baselibrary.widget

import android.content.Context
import android.widget.ImageView
import com.youth.banner.loader.ImageLoader
import ruolan.com.baselibrary.utils.GlideUtils

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class BannerImageLoader : ImageLoader() {

    override fun displayImage(context: Context, path: Any?, imageView: ImageView) {

        GlideUtils.loadUrlImage(context, path.toString(), imageView)

    }


}