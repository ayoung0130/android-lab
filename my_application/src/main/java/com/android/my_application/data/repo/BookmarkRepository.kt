package com.android.my_application.data.repo

import com.android.my_application.room.BookmarkEntity

interface BookmarkRepository {

    fun getAll(): List<BookmarkEntity>
    fun delete(entity: BookmarkEntity)
    fun insert(entity: BookmarkEntity)
}