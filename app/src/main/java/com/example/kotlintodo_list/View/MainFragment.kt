package com.example.kotlintodo_list.View

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintodo_list.Model.data.TaskAdapter
import com.example.kotlintodo_list.R
import com.example.kotlintodo_list.ViewModel.TaskViewModel
import com.example.kotlintodo_list.databinding.ActivityMainBinding
import com.example.kotlintodo_list.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)

        val adapter = TaskAdapter()
        val recyclerView = binding.taskRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.readAllData.observe(viewLifecycleOwner, Observer {task ->
            adapter.setData(task)
        })

        binding.addButton.setOnClickListener {

        }
    }
}