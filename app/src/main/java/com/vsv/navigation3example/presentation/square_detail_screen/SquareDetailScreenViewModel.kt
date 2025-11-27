package com.vsv.navigation3example.presentation.square_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsv.navigation3example.domain.SquareStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SquareDetailScreenViewModel(
    private val squareId: Int?
) : ViewModel() {

    private val storage = SquareStorage

    private val _state = MutableStateFlow(SquareDetailScreenState())
    val state = _state.asStateFlow()

    init {
        getSquareById()
    }

    fun onAction(action: SquareDetailScreenAction) {
        when (action) {
            is SquareDetailScreenAction.OnOtherSquareClick -> Unit
        }
    }

    private fun getSquareById() {
        if (squareId == null) return
        viewModelScope.launch {
            _state.update {
                it.copy(
                    currentSquare = storage.getSquareById(squareId)
                )
            }
            getOtherSquares()
        }
    }

    private fun getOtherSquares() {
        if (squareId == null) return
        viewModelScope.launch {
            _state.update {
                it.copy(
                    otherSquares = storage.getOtherSquares(squareId)
                )
            }
        }
    }
}