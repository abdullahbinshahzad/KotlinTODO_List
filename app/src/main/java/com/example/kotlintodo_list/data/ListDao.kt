package com.example.kotlintodo_list.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: ListEntity)

    @Query ("SELECT * FROM kotlintodo_list")
    suspend fun readList(): List<ListEntity>
}