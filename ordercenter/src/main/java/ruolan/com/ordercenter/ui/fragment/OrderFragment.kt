package ruolan.com.ordercenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.toast
import ruolan.com.baselibrary.ext.startLoading
import ruolan.com.baselibrary.ui.fragment.BaseFragment
import ruolan.com.ordercenter.R

/**
 * Created by wuyinlei on 2018/2/8.
 *
 * @function
 */
class OrderFragment : BaseFragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_order,container,false)
    }

    override fun onStart() {
        super.onStart()
        loadData()
        toast("请求数据了")
    }

    /*
       加载数据
    */
    private fun loadData() {
        mMultiStateView.startLoading()
    }


    private fun initView() {

    }
}