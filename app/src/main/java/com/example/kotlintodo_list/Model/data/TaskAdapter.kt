package com.example.kotlintodo_list.Model.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintodo_list.ItemClickListener
import com.example.kotlintodo_list.databinding.TaskCardTemplateBinding

class TaskAdapter(private val itemClickListener: ItemClickListener): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
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
            itemClickListener.onEditImageClick(currentItem)
//            val action = MainFragmentDirections.actionMainFragmentToUpdateTaskFragment(currentItem)
//            onclick.invoke(position)
//            findNavController().navigate(R.id.action_mainFragment_to_addTaskFragment)
        }
        holder.binding.deleteImageView.setOnClickListener {
            itemClickListener.onDeleteImageClick(currentItem)
        }

    }

    fun setData(task: List<Task>){
        this.taskList = task
        notifyDataSetChanged()
    }
}