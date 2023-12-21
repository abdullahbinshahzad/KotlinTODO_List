package com.example.kotlintodo_list.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
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
    private var taskID:Int?=null
    private var taskName:String?= null
    private var taskDescription:String ?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateTaskBinding.inflate(inflater, container,false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        taskId= arguments?.getInt("id").toString()
        Log.i("taskId", "onViewCreatedId: $taskId")
        taskID= taskId!!.toInt()
        Log.i("taskID", "onViewCreatedID: $taskID")
        taskName= arguments?.getString("title")
        taskDescription = arguments?.getString("description")
        if (taskName.isNullOrBlank()) {
            binding.UpdatetaskEditText.text = Editable.Factory.getInstance().newEditable("TASK NAME IS NULL OR EMPTY")
            binding.UpdatedescriptionEditText.text=Editable.Factory.getInstance().newEditable(taskDescription)
        }
        else if (taskDescription.isNullOrBlank()){
            binding.UpdatetaskEditText.text= Editable.Factory.getInstance().newEditable(taskName)
            binding.UpdatedescriptionEditText.text= Editable.Factory.getInstance().newEditable("TASK DESCRIPTION IS NULL OR EMPTY")
        }
        else {
            binding.UpdatetaskEditText.text= Editable.Factory.getInstance().newEditable(taskName)
            binding.UpdatedescriptionEditText.text=Editable.Factory.getInstance().newEditable(taskDescription)
        }

        Log.i("taskName", "onViewCreated: $taskName")
        Log.i("taskDescription", "onViewCreated: $taskDescription")

        binding.UpdateButton.setOnClickListener {
            updateDataToDatabase()
        }

        return binding.root
    }

    private fun updateDataToDatabase() {
        val task = binding.UpdatetaskEditText.text.toString()
        val description = binding.UpdatedescriptionEditText.text.toString()

        Log.i("taskName", "check id: $id")
        Log.i("taskName", "onViewCreated: $task")
        if (inputCheck(task, description)) {
            //create task object
            val task = Task(id, task, description)
            //update data to database
            taskViewModel.updateTask(task)
            Toast.makeText(requireContext(), "DONE", Toast.LENGTH_SHORT).show()
            Log.i("taskName", "after toast: $task")
            findNavController().navigate(R.id.action_updateTaskFragment_to_mainFragment)
        }
    }
    private fun inputCheck(task: String, description: String): Boolean {
        return !TextUtils.isEmpty(task) && !TextUtils.isEmpty(description)
    }
}