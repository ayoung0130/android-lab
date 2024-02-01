package com.android.my_application.ui.search

import androidx.lifecycle.MutableLiveData
import com.android.base.BaseViewModel
import com.android.my_application.data.repo.BookmarkRepository
import com.android.my_application.data.repo.KakaoRepository
import com.android.my_application.network.response.ImageResult
import com.android.my_application.network.response.toBookmarkEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel (
    private val kakaoRepository: KakaoRepository,
    private val bookmarkRepository: BookmarkRepository
) : BaseViewModel() {

    val inputSearchLiveData = MutableLiveData(EMPTY_STRING)

    fun searchImage() {
        if (!inputSearchLiveData.value.isNullOrEmpty()) {
            kakaoRepository.searchImage(
                query = inputSearchLiveData.value!!,
                sort = SORT_ACCURACY,
                size = DEFAULT_SIZE,
                page = DEFAULT_PAGE,
                callback = { list ->
                    if (list.isEmpty()) {
                        onChangedViewState(SearchViewState.EmptyResult)
                        onChangedViewState(SearchViewState.ShowToast("검색 결과가 없습니다."))
                    } else {
                        CoroutineScope(Dispatchers.IO).launch {
                            val bookmarkList = bookmarkRepository.getAll().map { it.number }

                            val convertBookmarkList = list.map {
                                it.copy(isBookmarked = bookmarkList.contains(it.number))
                            }
                            onChangedViewState(SearchViewState.GetImageList(convertBookmarkList))
                        }
                    }
                }
            )
        }
    }

    fun toggleBookmark(item: ImageResult) {
        CoroutineScope(Dispatchers.IO).launch {
            if (item.isBookmarked) {
                bookmarkRepository.delete(item.toBookmarkEntity())
            } else {
                bookmarkRepository.insert(item.toBookmarkEntity())
            }
            onChangedViewState(SearchViewState.ToggleBookmark(item))
        }
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val SORT_ACCURACY = "accuracy"
        private const val DEFAULT_SIZE = 20
        private const val DEFAULT_PAGE = 1
    }
}