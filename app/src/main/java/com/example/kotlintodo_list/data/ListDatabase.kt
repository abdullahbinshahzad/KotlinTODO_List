package com.example.kotlintodo_list.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListEntity::class], version = 1, exportSchema = false)
abstract class ListDatabase: RoomDatabase( ) {
    abstract fun listDao(): ListDao

    companion object{
        private var INSTANCE: ListDatabase? = null

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ListDatabase::class.java, "list_database"
        ).build()
    }
}