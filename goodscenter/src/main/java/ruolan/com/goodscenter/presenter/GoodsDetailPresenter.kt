package ruolan.com.goodscenter.presenter

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.goodscenter.data.protocol.Goods
import ruolan.com.goodscenter.presenter.view.GoodsDetailView
import ruolan.com.goodscenter.service.CartService
import ruolan.com.goodscenter.service.GoodsService
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService

    @Inject
    lateinit var cartService: CartService

    /*
           获取商品详情
        */
    fun getGoodsDetailList(goodsId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId).excute(object : BaseSubscriber<BaseResp<Goods>>(mView) {
            override fun onNext(t: BaseResp<Goods>) {
                mView.onGetGoodsDetailResult(t.data)
            }
        }, lifecycleProvider)

    }


    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String) {

        if (!checkNetWork()) {
            return
        }
        cartService.addCartGoods(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku)
                .excute(object : BaseSubscriber<BaseResp<Int>>(mView) {
                    override fun onNext(t: BaseResp<Int>) {
                        mView.onAddCartResult(t.data)
                    }
                }, lifecycleProvider)
    }


}