package com.example.kotlintodo_list.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintodo_list.model.Task
import com.example.kotlintodo_list.model.TaskDatabase
import com.example.kotlintodo_list.model.TaskRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): ViewModel() {

    val readAllData: LiveData<List<Task>>
    private val repository: TaskRepositoryImpl

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepositoryImpl(taskDao)
        readAllData = repository.readAllData
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }
}