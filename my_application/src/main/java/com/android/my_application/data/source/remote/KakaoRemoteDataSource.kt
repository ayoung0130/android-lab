package com.android.my_application.data.source.remote

import com.android.my_application.network.response.ImageResult


interface KakaoRemoteDataSource {
    fun searchImage(query: String, sort: String, page: Int, size: Int, callback: (List<ImageResult>)-> Unit)
}