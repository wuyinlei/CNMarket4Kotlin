package com.ruolan.user.service.impl

import com.ruolan.user.data.repository.UploadRespository
import com.ruolan.user.service.UploadService
import ruolan.com.baselibrary.ext.convert
import rx.Observable
import javax.inject.Inject

/**
 * Created by wuyinlei on 2018/1/20.
 *
 * @function
 */
class UploadServiceImpl @Inject constructor() : UploadService {


    override fun getUploadToken(): Observable<String> {
       return respository.upload().convert()
    }

    @Inject
    lateinit var respository: UploadRespository


}