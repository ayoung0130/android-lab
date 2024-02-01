package com.android.my_application.util

import com.android.my_application.network.KakaoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {

    private const val BASE_KAKAO_URL = "https://dapi.kakao.com/"

    fun getKAKAOService() = createService<KakaoService>(BASE_KAKAO_URL)
}

inline fun <reified T> createService(baseUrl: String): T =
    Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
        GsonConverterFactory.create()
    ).build().create(T::class.java)