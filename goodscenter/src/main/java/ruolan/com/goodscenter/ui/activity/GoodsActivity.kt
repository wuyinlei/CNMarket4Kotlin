package ruolan.com.goodscenter.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ruolan.user.injection.component.DaggerCategoryComponent
import kotlinx.android.synthetic.main.activity_goods.*
import org.jetbrains.anko.toast
import ruolan.com.baselibrary.ext.startLoading
import ruolan.com.baselibrary.ui.activity.BaseMvpActivity
import ruolan.com.goodscenter.R
import ruolan.com.goodscenter.common.GoodsConstant
import ruolan.com.goodscenter.data.protocol.Goods
import ruolan.com.goodscenter.injection.component.DaggerCoodsComponent
import ruolan.com.goodscenter.injection.module.CategoryModule
import ruolan.com.goodscenter.injection.module.GoodsModule
import ruolan.com.goodscenter.presenter.GoodsPresenter
import ruolan.com.goodscenter.presenter.view.GoodsView
import ruolan.com.goodscenter.ui.fragment.CartFragment

class GoodsActivity : BaseMvpActivity<GoodsPresenter>(), GoodsView {

    private var mCurrentPage: Int = 1

    override fun onGoodsResult(result: MutableList<Goods>) {

        toast("加载到的数据数量是:" + result.size)

    }

    override fun injectComponent() {

        DaggerCoodsComponent.builder()
                .activityComponent(mActivityComponent)
                .goodsModule(GoodsModule())
                .build().inject(this)


        mPresenter.mView = this

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initData()
    }

    private fun initData() {

        mMultiStateView.startLoading()
        mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 1), mCurrentPage)

    }


}