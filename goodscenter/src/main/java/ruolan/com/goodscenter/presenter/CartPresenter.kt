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

    fun getCartList() {
        if (!checkNetWork()) {
            return
        }
        cartService.getCartGoods()
                .excute(object : BaseSubscriber<BaseResp<MutableList<CartGoods>>>(mView) {
                    override fun onNext(t: BaseResp<MutableList<CartGoods>>) {
                        mView.onGetCartListResult(t.data)
                    }
                }, lifecycleProvider)
    }



    fun delCartList(list: List<Int>) {
        if (!checkNetWork()) {
            return
        }

        cartService.delCartGoods(list)
                .excute(object : BaseSubscriber<BaseResp<String>>(mView){
                    override fun onNext(t: BaseResp<String>) {
                        mView.onDeleteCartListResult(t.message.equals("删除商品成功"))
                    }
                },lifecycleProvider)

    }
}