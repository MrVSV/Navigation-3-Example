package com.vsv.navigation3example.presentation.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel() {

    private val _counterState = MutableStateFlow(0)
    val count = _counterState.asStateFlow()

    fun increment() {
        _counterState.update { it + 1 }
    }
}