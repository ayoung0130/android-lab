package com.android.my_application.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM image")
    fun getAll(): MutableList<BookmarkEntity>

    @Delete
    fun delete(entity: BookmarkEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: BookmarkEntity)

    @Update
    fun update(memo: BookmarkEntity)
}