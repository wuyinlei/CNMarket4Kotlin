package ruolan.com.goodscenter.presenter

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.goodscenter.data.protocol.Goods
import ruolan.com.goodscenter.presenter.view.GoodsDetailView
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


}