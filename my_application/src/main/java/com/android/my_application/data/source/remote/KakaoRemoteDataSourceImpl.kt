package com.android.my_application.data.source.remote

import com.android.my_application.network.KakaoService
import com.android.my_application.network.response.ImageResult
import com.android.my_application.network.response.KakaoSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoRemoteDataSourceImpl(private val kakaoService: KakaoService) : KakaoRemoteDataSource {
    override fun searchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
        callback: (List<ImageResult>) -> Unit
    ) {
        kakaoService.searchImage(
            query = query,
            sort = sort,
            page = page,
            size = size
        ).enqueue(object : Callback<KakaoSearchResponse> {
            override fun onResponse(
                call: Call<KakaoSearchResponse>,
                response: Response<KakaoSearchResponse>
            ) {
                if (response.isSuccessful) {
                    val images = response.body()?.documents ?: emptyList()
                    callback(images)
                } else {
                    callback(emptyList())
                }
            }

            override fun onFailure(call: Call<KakaoSearchResponse>, t: Throwable) {
                callback(emptyList())
            }
        })
    }

    companion object {
        private var INSTANCE: KakaoRemoteDataSource? = null

        fun getInstance(kakaoService: KakaoService): KakaoRemoteDataSource {
            return INSTANCE ?: KakaoRemoteDataSourceImpl(kakaoService).also {
                INSTANCE = it
            }
        }
    }


}