package com.android.my_application.data.repo

import com.android.my_application.data.source.remote.KakaoRemoteDataSource
import com.android.my_application.network.response.ImageResult

class KakaoRepositoryImpl(private val kakaoRemoteDataSource: KakaoRemoteDataSource) :
    KakaoRepository {
    override fun searchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
        callback: (List<ImageResult>) -> Unit
    ) {
        kakaoRemoteDataSource.searchImage(query, sort, page, size, callback)
    }

    companion object {
        private var INSTANCE: KakaoRepository? = null

        fun getInstance(kakaoRemoteDataSource: KakaoRemoteDataSource): KakaoRepository {
            return INSTANCE ?: KakaoRepositoryImpl(kakaoRemoteDataSource).also {
                INSTANCE = it
            }
        }
    }
}
