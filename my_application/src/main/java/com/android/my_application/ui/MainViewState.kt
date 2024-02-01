package com.android.my_application.ui

import com.android.base.ViewState
import com.android.my_application.room.BookmarkEntity

sealed interface MainViewState : ViewState {
    data class DeleteBookmark(
        val item: BookmarkEntity
    ) : MainViewState
}