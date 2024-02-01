package com.android.my_application.ui.bookmark

import com.android.base.BaseViewModel
import com.android.my_application.data.repo.BookmarkRepository
import com.android.my_application.room.BookmarkEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookmarkViewModel(private val bookmarkRepository: BookmarkRepository) : BaseViewModel() {

    fun getBookmarkList() {
        CoroutineScope(Dispatchers.IO).launch {
            val bookmarkList = bookmarkRepository.getAll()
            onChangedViewState(BookmarkViewState.GetBookmarkList(bookmarkList))
        }
    }

    fun deleteBookmark(bookmarkEntity: BookmarkEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            bookmarkRepository.delete(bookmarkEntity)

            withContext(Dispatchers.Main) {
                getBookmarkList()
            }
        }
    }
}