package br.com.messore.tech.turtleracing.presentation.turtle

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Timer
import java.util.TimerTask

class ClockViewModel : ViewModel() {
    private var timer: Timer = Timer()

    private val _timeMillis = MutableStateFlow(System.currentTimeMillis())
    val timeMillis: StateFlow<Long> get() = _timeMillis

    init {
        startCountDown()
    }

    private fun startCountDown() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                _timeMillis.value = System.currentTimeMillis()
            }
        }, 0, 250)
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}