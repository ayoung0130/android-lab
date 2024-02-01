package com.android.my_application.data.repo

import com.android.my_application.network.response.ImageResult

interface KakaoRepository {
    fun searchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
        callback: (List<ImageResult>) -> Unit
    )
}