package com.lib.lib_network.test

import android.app.Application
import android.widget.Toast
import com.lib.lib_network.RetrofitConfig
import com.lib.lib_network.ThreadSchedulers
import com.lib.lib_network.factory.LiveDataCallAdapterFactory

/**
 * 测试类
 *
 * @author: Fairy.
 * @date  : 2020/10/14.
 */
open class NetworkApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        RetrofitConfig.instance
            .debug(true)//是否测试环境
            .appName("Network")//输出日志Tag
            .baseUrl("https://api.lemoncoupon.cn")//请求URL
            .addHeaderParams("version_code", "100")//添加到header中公共参数
            .addHeaderParams("version_name", "1.0.0")
            .addUrlParams("token", "tokenValue")//添加公共参数,添加到URL末尾
            .addBodyParams(
                "param1",
                "paramValue1"
            )//添加到公共参数到消息体，适用Post(contentType=application/x-www-form-urlencoded)、Get请求
            .addBodyParams("param2", "paramValue2")
//            .addNetworkInterceptor(LoggingInterceptor())//添加网络拦截器，默认支撑LoggingInterceptor和GzipInterceptor
//            .addConverterFactory(GsonConverterFactory.create())//Json转换器，默认为Gson
//            .addCallAdapterFactory(LiveDataCallAdapterFactory() {})//添加数据源适配器，默认支撑LiveData和RxJava
            .addCallAdapterFactory(LiveDataCallAdapterFactory(ThreadSchedulers.MAIN_THREAD) {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            })
    }
}