package com.android.my_application.data.repo

import com.android.my_application.data.source.local.BookmarkLocalDataSource
import com.android.my_application.room.BookmarkEntity

class BookmarkRepositoryImpl(private val bookmarkLocalDataSource: BookmarkLocalDataSource) :
    BookmarkRepository {
    override fun getAll(): List<BookmarkEntity> =
        bookmarkLocalDataSource.getAll()

    override fun delete(entity: BookmarkEntity) {
        bookmarkLocalDataSource.delete(entity)
    }

    override fun insert(entity: BookmarkEntity) {
        bookmarkLocalDataSource.insert(entity)
    }

    companion object {
        private var INSTANCE: BookmarkRepository? = null

        fun getInstance(bookmarkLocalDataSource: BookmarkLocalDataSource): BookmarkRepository =
            INSTANCE ?: BookmarkRepositoryImpl(bookmarkLocalDataSource).apply { INSTANCE = this }
    }

}