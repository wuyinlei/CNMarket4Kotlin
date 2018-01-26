package ruolan.com.baselibrary.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.support.v4.toast
import ruolan.com.baselibrary.common.BaseApplication
import ruolan.com.baselibrary.injection.component.ActivityComponent
import ruolan.com.baselibrary.injection.component.DaggerActivityComponent
import ruolan.com.baselibrary.injection.module.ActivityModule
import ruolan.com.baselibrary.injection.module.LifecycleProviderModule
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> :BaseFragment(), BaseView {

    override fun onError(message: String) {
        toast(message)
    }

    override fun hideLoading() {


    }

    override fun showLoading() {


    }


    lateinit var mActivityComponent: ActivityComponent

    @Inject
    lateinit var mPresenter: T

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        initActivityComponent()

        injectComponent()

        return super.onCreateView(inflater, container, savedInstanceState)!!
    }


    abstract fun injectComponent()



    private fun initActivityComponent() {

        mActivityComponent = DaggerActivityComponent.builder().appComponent((activity.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()

    }
}