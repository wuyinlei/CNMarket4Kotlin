package ruolan.com.goodscenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_cart.*
import ruolan.com.baselibrary.ext.setVisible
import ruolan.com.baselibrary.ui.fragment.BaseMvpFragment
import ruolan.com.goodscenter.R
import ruolan.com.goodscenter.data.protocol.CartGoods
import ruolan.com.goodscenter.presenter.CartPresenter
import ruolan.com.goodscenter.presenter.view.CartView

/**
 * Created by wuyinlei on 2018/1/26.
 *
 * @function
 */
class CartFragment : BaseMvpFragment<CartPresenter>(), CartView {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun initView() {



    }

    private fun initData() {



    }

    /*
       设置Back是否可见
    */
    fun setBackVisible(isVisible: Boolean) {
        mHeaderBar.getLeftView().setVisible(isVisible)
    }


    override fun onGetCartListResult(result: MutableList<CartGoods>?) {


    }

    override fun onDeleteCartListResult(result: Boolean) {

    }

    override fun onSubmitCartListResult(result: Int) {

    }

    override fun injectComponent() {

    }


}