package com.android.my_application.ui

import com.android.base.BaseViewModel
import com.android.my_application.room.BookmarkEntity

class MainViewModel : BaseViewModel() {

    fun deleteBookmark(item: BookmarkEntity) {
        onChangedViewState(MainViewState.DeleteBookmark(item))
    }
}