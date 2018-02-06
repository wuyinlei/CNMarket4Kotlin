package ruolan.com.goodscenter.presenter

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.goodscenter.data.protocol.CartGoods
import ruolan.com.goodscenter.presenter.view.CartView
import ruolan.com.goodscenter.service.impl.CartServiceImpl
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
class CartPresenter @Inject constructor() : BasePresenter<CartView>() {


    @Inject
    lateinit var cartService: CartServiceImpl

    /**
     * 获取到购物车集合
     */
    fun getCartList() {
        if (!checkNetWork()) {
            return
        }
        cartService.getCartGoodsList()
                .excute(object : BaseSubscriber<BaseResp<MutableList<CartGoods>>>(mView) {
                    override fun onNext(t: BaseResp<MutableList<CartGoods>>) {
                        if (t.status == -2) {
                            mView.onNoPermission()
                        } else {
                            mView.onGetCartListResult(t.data)
                        }
                    }

                }, lifecycleProvider)
    }


    /**
     * 删除购物车
     */
    fun delCartList(list: List<Int>) {
        if (!checkNetWork()) {
            return
        }

        cartService.delCartGoods(list)
                .excute(object : BaseSubscriber<BaseResp<String>>(mView) {
                    override fun onNext(t: BaseResp<String>) {
                        mView.onDeleteCartListResult(t.message.equals("删除商品成功"))
                    }
                }, lifecycleProvider)

    }

    /**
     * 提交购物车
     */
    fun submitCartList(list: MutableList<CartGoods>, mTotalPrice: Long) {
        if (!checkNetWork()) {
            return
        }
        cartService.submitCart(list, mTotalPrice)
                .excute(object : BaseSubscriber<Int>(mView) {
                    override fun onNext(t: Int) {
                        mView.onSubmitCartListResult(t)
                    }
                }, lifecycleProvider)
    }
}