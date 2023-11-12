package com.example.kotlintodo_list.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kotlintodo_list.Model.data.Task
import com.example.kotlintodo_list.Model.data.TaskDatabase
import com.example.kotlintodo_list.Model.data.TaskRepository
import com.example.kotlintodo_list.ViewModel.TaskViewModel
import com.example.kotlintodo_list.databinding.ActivityAddtaskBinding

class AddtaskActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: ActivityAddtaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddtaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = TaskRepository(TaskDatabase.getDatabase(this).taskDao())
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.doneButton.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val task = binding.taskEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()

        if (inputCheck(task, description)) {
            //create task object
            val task = Task(0, task, description)
            //add data to database
            taskViewModel.addTask(task)
            Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "PLZ COMPLETE YOUR CREDENTIALS", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(task: String, description: String): Boolean {
        return !TextUtils.isEmpty(task) && !TextUtils.isEmpty(description)
    }
}