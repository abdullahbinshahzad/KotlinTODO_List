package com.example.kotlintodo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlintodo_list.databinding.ActivityAddnoteBinding
import com.example.kotlintodo_list.databinding.ActivityMainBinding

class AddnoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddnoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddnoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.doneButton.setOnClickListener {

        }
    }
}