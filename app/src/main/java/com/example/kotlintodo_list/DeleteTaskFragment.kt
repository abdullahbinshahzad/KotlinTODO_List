package com.example.kotlintodo_list

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlintodo_list.Model.data.Task
import com.example.kotlintodo_list.ViewModel.TaskViewModel
import com.example.kotlintodo_list.databinding.FragmentDeleteTaskBinding

class DeleteTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: FragmentDeleteTaskBinding
    private var taskId:String?=null
    private var taskID:Int?=null
    private lateinit var taskName:String
    private lateinit var taskDescription:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeleteTaskBinding.inflate(inflater, container, false)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
//        deleteDataFromDatabase()

        taskId= arguments?.getInt("id").toString()
        taskID= taskId!!.toInt()
        taskName= arguments?.getString("title").toString()
        taskDescription = arguments?.getString("description").toString()

        Log.i("taskName", "onViewCreated: $taskName")
        Log.i("taskDescription", "onViewCreated: $taskDescription")

        if (inputCheck(taskName, taskDescription)) {
            val task = Task(taskID!!, taskName,taskDescription)
            taskViewModel.deleteTask(task)

            Toast.makeText(requireContext(), "task DELETED", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_deleteTaskFragment_to_mainFragment)
        }
        return binding.root
    }

    private fun inputCheck(task: String, description: String): Boolean {
        return !TextUtils.isEmpty(task) && !TextUtils.isEmpty(description)
    }


}