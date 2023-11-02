package com.example.kotlintodo_list.Model.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application, private val repository: TaskRepository): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Task>> = repository.readAllData

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }
    }