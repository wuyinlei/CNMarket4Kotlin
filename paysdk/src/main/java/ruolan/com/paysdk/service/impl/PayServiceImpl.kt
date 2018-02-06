package ruolan.com.paysdk.service.impl

import ruolan.com.baselibrary.ext.convert
import ruolan.com.baselibrary.ext.convertBoolean
import ruolan.com.paysdk.data.repository.PayRespository
import ruolan.com.paysdk.service.PayService
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/2/6.
 *
 * @function
 */
class PayServiceImpl @Inject constructor() : PayService {

    @Inject
    lateinit var repository: PayRespository


    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<String> {

       return repository.getPaySign(orderId,totalPrice).convert()

    }

    override fun payOrder(orderId: Int): Observable<Boolean> {
        return repository.payOrder(orderId).convertBoolean()
    }


}