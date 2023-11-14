package com.example.kotlintodo_list.Model.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintodo_list.R
import com.example.kotlintodo_list.View.fragments.MainFragmentDirections
import com.example.kotlintodo_list.databinding.TaskCardTemplateBinding

class TaskAdapter(val onclick : (Int) -> Unit): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
     private var taskList = emptyList<Task>()
    class ViewHolder(val binding: TaskCardTemplateBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TaskCardTemplateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.binding.taskTextView.text = currentItem.taskName
        holder.binding.descriptionTextView.text = currentItem.task_Description

        holder.binding.editImageView.setOnClickListener {
            onclick.invoke(position)
//            val action = MainFragmentDirections.actionMainFragmentToUpdateTaskFragment(currentItem)
//            findNavController().navigate(R.id.action_mainFragment_to_addTaskFragment)
        }

    }

    fun setData(task: List<Task>){
        this.taskList = task
        notifyDataSetChanged()
    }
}