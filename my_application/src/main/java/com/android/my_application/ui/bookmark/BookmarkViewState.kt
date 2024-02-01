package com.android.my_application.ui.bookmark

import com.android.base.ViewState
import com.android.my_application.room.BookmarkEntity

sealed interface BookmarkViewState : ViewState {
    data class GetBookmarkList(val items: List<BookmarkEntity>) : BookmarkViewState
}