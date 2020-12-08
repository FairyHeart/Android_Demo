package com.lib.lib_network.test

import androidx.lifecycle.LiveData
import com.lib.lib_network.test.dto.LoginDto
import com.lib.android_lib_network.dto.PayDto
import com.lib.lib_network.dto.ResultDto
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/9/10.
 */
interface IRemoteService {

    /**
     * 手机号密码登录
     */
    @POST("weChat/v1/mobile_login")
    fun loginByPhoneObservable(
        @Body info: RequestBody
    ): Observable<ResultDto<LoginDto>>


    @Throws
    @POST("weChat/v1/mobile_login")
    fun loginByPhoneLiveData(
        @Body info: RequestBody
    ): LiveData<ResultDto<LoginDto?>>

    /**
     *  获取支付流水
     */
    @POST("order/v1/get_order_pay_list")
    fun getPayList(
        @Body info: RequestBody,
        @Query("xtoken") token: String
    ): Observable<ResultDto<MutableList<PayDto>?>>

}