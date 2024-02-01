package com.android.my_application.ui.search

import com.android.base.ViewState
import com.android.my_application.network.response.ImageResult


sealed interface SearchViewState : ViewState {
    object EmptyResult : SearchViewState
    data class ShowToast(val message: String) : SearchViewState
    data class GetImageList(val imageList: List<ImageResult>) : SearchViewState
    data class ToggleBookmark(val item: ImageResult) : SearchViewState
}