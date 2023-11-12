package com.example.kotlintodo_list.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintodo_list.Model.data.TaskAdapter
import com.example.kotlintodo_list.ViewModel.TaskViewModel
import com.example.kotlintodo_list.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TaskAdapter()
        val recyclerView = binding.taskRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.readAllData.observe(this, Observer {task ->
            adapter.setData(task)
        })

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddtaskActivity::class.java)
            startActivity(intent)
        }
    }
}