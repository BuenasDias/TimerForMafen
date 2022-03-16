package com.max.interview.timerformafen.screens.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.max.interview.timerformafen.R
import com.max.interview.timerformafen.databinding.ActivityMainBinding
import com.max.interview.timerformafen.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val viewModel : SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObservers()
    }

    private fun initObservers() {
        viewModel.editText.observe(this){
            updateTextView(it)
        }
    }

    private fun updateTextView(value: String) {
        binding.textResult.text = value
    }

    private fun initView() {
        binding.editText.addTextChangedListener {
            viewModel.updateEditText(it.toString())
        }
    }
}