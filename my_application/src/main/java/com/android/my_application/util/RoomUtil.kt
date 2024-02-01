package com.android.my_application.util

import android.content.Context
import androidx.room.Room
import com.android.my_application.room.BookmarkDao
import com.android.my_application.room.BookmarkDatabase

object RoomUtil {

    private lateinit var db: BookmarkDatabase

    fun createDb(context: Context) {
        db = Room.databaseBuilder(
            context = context,
            klass = BookmarkDatabase::class.java,
            "image"
        ).build()
    }

    fun getBookmarkDao(): BookmarkDao {
        return db.bookmarkDao()
    }
}