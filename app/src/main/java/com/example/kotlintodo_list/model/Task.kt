package com.example.kotlintodo_list.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "task_table")
@Parcelize  //allowing instances of the class to be passed between components in Android
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val taskName: String,
    val taskDescription: String
): Parcelable