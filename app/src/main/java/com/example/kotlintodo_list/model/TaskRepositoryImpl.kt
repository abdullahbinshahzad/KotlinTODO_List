package com.example.kotlintodo_list.model

import androidx.lifecycle.LiveData

class TaskRepositoryImpl(private val taskDao: TaskDao):TaskRepository {
    val readAllData : LiveData<List<Task>> = taskDao.readAllData()

    override suspend fun addTask(task: Task){
        taskDao.readAllData()
        taskDao.addTask(task)
    }

    override suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    override suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
}