package com.example.passeio.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class CounterState(val count: Int = 0)

sealed interface CounterEvent {
    data object Increment : CounterEvent
}

class CounterViewModel : ViewModel() {
    private val stateFlow = MutableStateFlow(CounterState())

    fun getState(): StateFlow<CounterState> = stateFlow

    fun handle(event: CounterEvent) {
        when (event) {
            CounterEvent.Increment -> handleIncrementEvent()
        }
    }

    private fun handleIncrementEvent() {
        val newCount: Int = stateFlow.value.count + 1
        stateFlow.tryEmit(CounterState(newCount))
    }
}
