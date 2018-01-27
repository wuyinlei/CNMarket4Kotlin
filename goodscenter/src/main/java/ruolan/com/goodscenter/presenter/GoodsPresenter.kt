package ruolan.com.goodscenter.presenter

import ruolan.com.baselibrary.data.model.BaseResp
import ruolan.com.baselibrary.ext.excute
import ruolan.com.baselibrary.presenter.BasePresenter
import ruolan.com.baselibrary.rx.BaseSubscriber
import ruolan.com.goodscenter.data.protocol.Category
import ruolan.com.goodscenter.data.protocol.Goods
import ruolan.com.goodscenter.presenter.view.CategoryView
import ruolan.com.goodscenter.presenter.view.GoodsView
import ruolan.com.goodscenter.service.impl.CategoryServiceImpl
import ruolan.com.goodscenter.service.impl.GoodsServiceImpl
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
class GoodsPresenter @Inject constructor() : BasePresenter<GoodsView>() {


    @Inject
    lateinit var  goodsService: GoodsServiceImpl

    fun getGoodsList(categoryId:Int,pageNo:Int) {

        goodsService.getGoods(categoryId,pageNo)
                .excute(object : BaseSubscriber<BaseResp<MutableList<Goods>>>(mView){
                    override fun onNext(t: BaseResp<MutableList<Goods>>) {
                        mView.onGoodsResult(t.data)
                    }
                },lifecycleProvider)

    }
}