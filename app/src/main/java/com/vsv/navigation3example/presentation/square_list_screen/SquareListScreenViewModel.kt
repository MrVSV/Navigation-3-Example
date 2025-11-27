package com.vsv.navigation3example.presentation.square_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsv.navigation3example.domain.SquareStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SquareListScreenViewModel : ViewModel() {

    private val storage = SquareStorage

    private val _state = MutableStateFlow(SquareListScreenState())
    val state = _state.asStateFlow()

    init {
        getSquares()
    }

    fun onAction(action: SquareListScreenAction) {
        when (action) {
            is SquareListScreenAction.OnSquareClick -> Unit
            SquareListScreenAction.OnCircleClick -> Unit
        }
    }

    private fun getSquares() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    squares = storage.getAllSquares()
                )
            }
        }
    }

}