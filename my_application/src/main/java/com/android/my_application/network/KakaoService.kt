package com.android.my_application.network

import com.android.my_application.network.response.KakaoSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoService {

    @Headers("Authorization: KakaoAK $REST_API_KEY")
    @GET("v2/search/image")
    fun searchImage(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20
    ): Call<KakaoSearchResponse>

    companion object {
        private const val REST_API_KEY = "c9cf0cccb7a4d43e14f4d338dde58a7b"
    }

}