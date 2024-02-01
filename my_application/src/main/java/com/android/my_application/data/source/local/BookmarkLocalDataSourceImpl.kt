package com.android.my_application.data.source.local

import com.android.my_application.room.BookmarkDao
import com.android.my_application.room.BookmarkEntity

class BookmarkLocalDataSourceImpl(private val bookmarkDao: BookmarkDao): BookmarkLocalDataSource {
    override fun getAll(): List<BookmarkEntity> =
        bookmarkDao.getAll()

    override fun delete(entity: BookmarkEntity) {
        bookmarkDao.delete(entity)
    }

    override fun insert(entity: BookmarkEntity) {
        bookmarkDao.insert(entity)
    }

    companion object {
        private var INSTANCE: BookmarkLocalDataSource? = null

        fun getInstance(bookmarkDao: BookmarkDao): BookmarkLocalDataSource =
            INSTANCE ?: BookmarkLocalDataSourceImpl(bookmarkDao).apply { INSTANCE = this }
    }

}