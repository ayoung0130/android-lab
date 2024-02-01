package com.android.my_application.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.my_application.data.repo.BookmarkRepository
import com.android.my_application.util.InjectUtil

class BookmarkViewModelFactory(private val bookmarkRepository: BookmarkRepository = InjectUtil.provideBookmarkRepository()) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java))
            return BookmarkViewModel(bookmarkRepository) as T
        else throw IllegalArgumentException()
    }
}