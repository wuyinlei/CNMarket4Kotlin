package ruolan.com.baselibrary.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.common.BaseApplication
import ruolan.com.baselibrary.injection.component.ActivityComponent
import ruolan.com.baselibrary.injection.component.DaggerActivityComponent
import ruolan.com.baselibrary.injection.module.ActivityModule
import ruolan.com.baselibrary.injection.module.LifecycleProviderModule
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function  mvp activity基类
 *
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    override fun hideLoading() {
    }

    override fun onError(message: String) {
        toast(message)
    }

    override fun showLoading() {
    }

    lateinit var mActivityComponent: ActivityComponent

    @Inject
    lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityComponent()

        injectComponent()


    }

    abstract fun injectComponent()


    private fun initActivityComponent() {

        mActivityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()

    }
}