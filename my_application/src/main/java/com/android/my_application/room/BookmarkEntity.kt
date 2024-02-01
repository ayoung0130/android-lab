package com.android.my_application.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class BookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "collection")
    val collection: String,
)