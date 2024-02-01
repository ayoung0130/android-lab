package com.android.my_application.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.my_application.data.repo.BookmarkRepository
import com.android.my_application.data.repo.KakaoRepository
import com.android.my_application.util.InjectUtil

class SearchViewModelFactory(
    private val kakaoRepository: KakaoRepository = InjectUtil.provideKakaoRepository(),
    private val bookmarkRepository: BookmarkRepository = InjectUtil.provideBookmarkRepository()
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java))
            return SearchViewModel(kakaoRepository, bookmarkRepository) as T
        else throw IllegalArgumentException()
    }
}