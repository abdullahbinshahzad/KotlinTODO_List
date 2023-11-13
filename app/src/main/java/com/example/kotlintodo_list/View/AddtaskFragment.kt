package com.example.kotlintodo_list.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlintodo_list.Model.data.Task
import com.example.kotlintodo_list.Model.data.TaskDatabase
import com.example.kotlintodo_list.Model.data.TaskRepository
import com.example.kotlintodo_list.R
import com.example.kotlintodo_list.ViewModel.TaskViewModel
import com.example.kotlintodo_list.databinding.FragmentAddtaskBinding

class AddtaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: FragmentAddtaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_addtask, container, false)

        val repository = TaskRepository(TaskDatabase.getDatabase(requireContext()).taskDao())
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
            Toast.makeText(requireContext(), "DONE", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addtaskFragment_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "PLZ COMPLETE YOUR CREDENTIALS", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(task: String, description: String): Boolean {
        return !TextUtils.isEmpty(task) && !TextUtils.isEmpty(description)
    }
}