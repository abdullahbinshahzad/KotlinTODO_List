package com.example.kotlintodo_list.View.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlintodo_list.Model.data.Task
import com.example.kotlintodo_list.R
import com.example.kotlintodo_list.ViewModel.TaskViewModel
import com.example.kotlintodo_list.databinding.FragmentUpdateTaskBinding

class UpdateTaskFragment : Fragment() {

//    private val args: UpdateTaskFragmentArgs by navArgs()

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: FragmentUpdateTaskBinding
    private var taskId:String?=null
    private var taskID:Int?=null
    private var taskName:String?= null
    private var taskDescription:String ?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateTaskBinding.inflate(inflater, container,false)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

//        Log.d("Arguments","value =${args.currentItem}")
//        binding.UpdatetaskEditText.setText(args.currentItem?.taskName ?: "no task")
//        binding.UpdatedescriptionEditText.setText(args.currentItem?.task_Description ?: "no description")
//        Log.i("taskName", "onViewCreated: $taskName")
//        Log.i("taskDescription", "onViewCreated: $taskDeascription")


        taskId= arguments?.getInt("id").toString()
        taskID= taskId!!.toInt()
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

        if (inputCheck(task, description)) {
            //create task object
            val task = taskID?.let { Task(it, task, description) }
            //add data to database
            if (task != null) {
                taskViewModel.addTask(task)
            }
            Toast.makeText(requireContext(), "DONE", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateTaskFragment_to_mainFragment)
        }
    }
    private fun inputCheck(task: String, description: String): Boolean {
        return !TextUtils.isEmpty(task) && !TextUtils.isEmpty(description)
    }
}