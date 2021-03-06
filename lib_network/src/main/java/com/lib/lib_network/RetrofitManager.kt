package com.lib.lib_network

import com.lib.lib_network.interceptor.GzipInterceptor
import com.lib.lib_network.interceptor.HttpCommonInterceptor
import com.lib.lib_network.interceptor.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020-01-07.
 */

class RetrofitManager private constructor() {

    companion object {
        val instance: RetrofitManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitManager()
        }
    }

    private var mRetrofit: Retrofit

    init {
        mRetrofit = createRetrofit()
    }

    /**
     * 重新创建Retrofit
     *      当配置参数更改之后，需要重新创建
     */
    fun reCreateRetrofit() {
        this.mRetrofit = this.createRetrofit()
    }

    /**
     * 创建Retrofit
     */
    private fun createRetrofit(): Retrofit {
        //添加公用参数到头部或者公用参数里面
        val commonBuilder = HttpCommonInterceptor.Builder()
        val headerParams = RetrofitConfig.instance.headerParams
        if (!headerParams.isNullOrEmpty()) {
            headerParams.keys.forEach {
                headerParams[it]?.let { value -> commonBuilder.addHeaderParams(it, value) }
            }
        }
        val urlParams = RetrofitConfig.instance.urlParams
        if (!urlParams.isNullOrEmpty()) {
            urlParams.keys.forEach {
                urlParams[it]?.let { it1 -> commonBuilder.addUrlParams(it, it1) }
            }
        }
        val bodyParams = RetrofitConfig.instance.bodyParams
        if (!bodyParams.isNullOrEmpty()) {
            bodyParams.keys.forEach {
                bodyParams[it]?.let { it1 -> commonBuilder.addBodyParams(it, it1) }
            }
        }
        val commonInterceptor = commonBuilder.build()

        val builder = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(commonInterceptor)//应用拦截器
            .addNetworkInterceptor(GzipInterceptor())//网络拦截器
            .addNetworkInterceptor(LoggingInterceptor())

        //添加拦截器
        val networkInterceptors = RetrofitConfig.instance.networkInterceptors
        if (!networkInterceptors.isNullOrEmpty()) {
            networkInterceptors.forEach {
                builder.addNetworkInterceptor(it)
            }
        }
        val client = builder.build()

        val retrofitBuilder = Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(RetrofitConfig.instance.baseUrl)

        if (RetrofitConfig.instance.converterFactory != null) {
            retrofitBuilder.addConverterFactory(RetrofitConfig.instance.converterFactory!!)
        } else {
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
        }
        if (RetrofitConfig.instance.callAdapterFactory != null) {
            retrofitBuilder.addCallAdapterFactory(RetrofitConfig.instance.callAdapterFactory!!)
        }
        return retrofitBuilder.build()
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return </T>
     */
    fun <T> create(service: Class<T>): T {
        return mRetrofit.create(service)
    }

}