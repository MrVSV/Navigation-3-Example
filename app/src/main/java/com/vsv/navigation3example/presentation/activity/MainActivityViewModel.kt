package com.vsv.navigation3example.presentation.activity

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainActivityViewModel: ViewModel() {

    private val _state = MutableStateFlow(MainActivityState())
    val state = _state.asStateFlow()

    fun onAction(action: MainActivityAction) {
        when(action) {
            is MainActivityAction.OnBottomTabClick -> {
                _state.update { it.copy(currentTab = action.tab) }
            }
        }
    }
}