package com.example.kotlintodo_list.View.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintodo_list.ItemClickListener
import com.example.kotlintodo_list.Model.data.Task
import com.example.kotlintodo_list.Model.data.TaskAdapter
import com.example.kotlintodo_list.R
import com.example.kotlintodo_list.ViewModel.TaskViewModel
import com.example.kotlintodo_list.databinding.FragmentMainBinding

class MainFragment : Fragment(),ItemClickListener {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)


        val adapter=TaskAdapter(this)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        taskViewModel.readAllData.observe(viewLifecycleOwner, Observer {task ->
            adapter.setData(task)
        })
        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addTaskFragment)
        }

        return binding.root
    }

    override fun onEditImageClick(task: Task) {
        val bundle=Bundle()
        bundle.putInt("id",task.id)
        bundle.putString("title",task.taskName)
        bundle.putString("description",task.task_Description)
        findNavController().navigate(R.id.action_mainFragment_to_updateTaskFragment,bundle)
    }

    override fun onDeleteImageClick(task: Task) {
        //val task = Task()
        val bundle=Bundle()
        bundle.putInt("id",task.id)
        bundle.putString("title",task.taskName)
        bundle.putString("description",task.task_Description)
        findNavController().navigate(R.id.action_mainFragment_to_deleteTaskFragment,bundle)
    }
}