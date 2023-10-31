package com.example.kotlintodo_list.data

import androidx.room.Entity

@Entity (tableName = "KotlinTODO_List")
data class ListEntity(

    val id: Int,
    val task: String,
    val description: String
)