package com.example.kotlintodo_list.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintodo_list.ItemClickListener
import com.example.kotlintodo_list.databinding.TaskCardTemplateBinding

class TaskAdapter(private val itemClickListener: ItemClickListener) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(
    Comparator()
) {


    class TaskViewHolder(val binding: TaskCardTemplateBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskCardTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.taskTextView.text = item.taskName
        holder.binding.descriptionTextView.text = item.taskDescription
        holder.binding.editImageView.setOnClickListener {
            itemClickListener.onEditImageClick(item)
        }
        holder.binding.deleteImageView.setOnClickListener {
            itemClickListener.onDeleteImageClick(item)
        }
    }


    class Comparator : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}