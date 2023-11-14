package com.example.kotlintodo_list.View.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.kotlintodo_list.R
import com.example.kotlintodo_list.databinding.FragmentUpdateTaskBinding

class UpdateTaskFragment : Fragment() {

    private val args by navArgs<UpdateTaskFragmentArgs>()
    private lateinit var binding: FragmentUpdateTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateTaskBinding.inflate(inflater, container,false)
        binding.UpdatetaskEditText.setText(args.currentTask.taskName)
        binding.UpdatedescriptionEditText.setText(args.currentTask.task_Description)
        return binding.root
    }
}