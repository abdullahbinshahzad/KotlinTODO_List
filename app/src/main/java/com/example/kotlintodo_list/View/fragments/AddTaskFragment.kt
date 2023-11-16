package com.example.kotlintodo_list.View.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlintodo_list.Model.data.Task
import com.example.kotlintodo_list.Model.data.TaskDatabase
import com.example.kotlintodo_list.Model.data.TaskRepository
import com.example.kotlintodo_list.R
import com.example.kotlintodo_list.ViewModel.TaskViewModel
import com.example.kotlintodo_list.databinding.FragmentAddTaskBinding

class AddTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        binding.doneButton.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val task = binding.taskEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()

        if (inputCheck(task, description)) {
            //create task object
            val task = Task(0, task, description)
            //add data to database
            taskViewModel.addTask(task)
            Toast.makeText(requireContext(), "DONE", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addTaskFragment_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "PLZ COMPLETE YOUR CREDENTIALS", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(task: String, description: String): Boolean {
        return !TextUtils.isEmpty(task) && !TextUtils.isEmpty(description)
    }
}