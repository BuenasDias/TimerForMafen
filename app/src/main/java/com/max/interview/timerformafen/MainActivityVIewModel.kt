package com.max.interview.timerformafen

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityVIewModel : ViewModel() {

    private var timer: CountDownTimer? = null

    private val _seconds: MutableLiveData<Long> = MutableLiveData(0)
    var second: LiveData<Long> = _seconds

    private val _isTimer: MutableLiveData<Boolean> = MutableLiveData(false)
    var isTimer: LiveData<Boolean> = _isTimer

    private val _isPause: MutableLiveData<Boolean> = MutableLiveData(true)
    var isPause: LiveData<Boolean> = _isPause

    val timerOriginal = 10000L

    private val _timerCurrent: MutableLiveData<Long> = MutableLiveData(10000L)

    fun createTimer() {

        _isTimer.postValue(true)
        _isPause.postValue(false)

        timer = object : CountDownTimer(_timerCurrent.value!!, 1000) {
            override fun onTick(p0: Long) {
                _seconds.postValue(p0 / 1000)
            }

            override fun onFinish() {
                _isTimer.postValue(false)
                _isPause.postValue(true)
                _timerCurrent.postValue(timerOriginal)
            }
        }.start()
    }

    fun pauseTimer() {
        timer?.cancel()
        _isPause.postValue(true)
        _timerCurrent.postValue(_seconds.value!! * 1000)
    }

    fun stopTimer() {
        timer?.cancel()
        _isTimer.postValue(false)
        _isPause.postValue(true)
        _seconds.postValue(0L)
    }
}