package com.example.kotlintodo_list

import com.example.kotlintodo_list.model.Task

interface ItemClickListener {
    fun onEditImageClick(task: Task)

    fun onDeleteImageClick(task: Task)
}