package ruolan.com.baselibrary.data.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ruolan.com.baselibrary.common.Constants
import java.util.concurrent.TimeUnit

/**
 * Created by wuyinlei on 2018/1/19.
 *
 * @function
 */
class RetrofitFactory private constructor() {

    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit
    private val interceptor:Interceptor

    /**
     * 初始化retrofit
     */
    init {

        interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Content_Type","application/json")
                    .addHeader("charset","UTF-8")
                    .build()

            chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
                .baseUrl(Constants.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    /**
     * 初始化OkHttpClient
     */
    private fun initClient(): OkHttpClient {

       return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(initLogInterceptor())
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .build()
    }

    /**
     * 初始化LogInterceptor
     */
    private fun initLogInterceptor(): Interceptor {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }

    /*
       具体服务实例化
    */
    fun <T> create(service:Class<T>):T{
        return retrofit.create(service)
    }

}