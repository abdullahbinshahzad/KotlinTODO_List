package com.example.kotlintodo_list.model

interface TaskRepository {
    suspend fun addTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
}