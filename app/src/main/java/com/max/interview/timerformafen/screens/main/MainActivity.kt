package com.max.interview.timerformafen.screens.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.max.interview.timerformafen.databinding.ActivityMainBinding

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityVIewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObservers()
    }

    private fun initObservers() {

        with(viewModel) {
            second.observe(this@MainActivity) { updateText(it) }

            isPause.observe(this@MainActivity) {
                if (!it) binding.btnStart.text = "pause timer"
                else binding.btnStart.text = "start timer"
            }
        }
    }

    private fun updateText(value: Any?) {
        binding.textTimer.text = value.toString()
    }

    private fun initView() {
        with(binding) {
            btnStart.setOnClickListener {
                if (viewModel.isPause.value!!) viewModel.createTimer()
                else viewModel.pauseTimer()
            }

            btnStop.setOnClickListener {
                viewModel.stopTimer()
            }
        }
    }
}