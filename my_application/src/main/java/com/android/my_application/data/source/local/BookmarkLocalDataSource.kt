package com.android.my_application.data.source.local

import com.android.my_application.room.BookmarkEntity

interface BookmarkLocalDataSource {

    fun getAll(): List<BookmarkEntity>
    fun delete(entity: BookmarkEntity)
    fun insert(entity: BookmarkEntity)
}