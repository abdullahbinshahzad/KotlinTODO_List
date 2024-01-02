package com.example.kotlintodo_list.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlintodo_list.R
import com.example.kotlintodo_list.viewmodel.TaskViewModel
import com.example.kotlintodo_list.databinding.FragmentUpdateTaskBinding
import com.example.kotlintodo_list.model.Task

class UpdateTaskFragment : Fragment() {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: FragmentUpdateTaskBinding
    private var taskId:String?=null
    private var taskID:Int = 0
    private var taskName:String?= null
    private var taskDescription:String ?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateTaskBinding.inflate(inflater, container,false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        taskId= arguments?.getInt("id").toString()
        taskID= taskId!!.toInt()
        taskName= arguments?.getString("title")
        taskDescription = arguments?.getString("description")
        binding.UpdateTaskEditText.text= Editable.Factory.getInstance().newEditable(taskName)
        binding.UpdateDescriptionEditText.text=Editable.Factory.getInstance().newEditable(taskDescription)

        binding.UpdateButton.setOnClickListener {
            updateDataToDatabase()
        }
        return binding.root
    }

    private fun updateDataToDatabase() {
        val task = binding.UpdateTaskEditText.text.toString()
        val description = binding.UpdateDescriptionEditText.text.toString()

        if (inputCheck(task, description)) {
            //create task object
            val task = Task(taskID, task, description)
            //update data to database
            taskViewModel.updateTask(task)
            Toast.makeText(requireContext(), "DONE", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateTaskFragment_to_mainFragment)
        }
    }
    private fun inputCheck(task: String, description: String): Boolean {
        return !TextUtils.isEmpty(task) && !TextUtils.isEmpty(description)
    }
}