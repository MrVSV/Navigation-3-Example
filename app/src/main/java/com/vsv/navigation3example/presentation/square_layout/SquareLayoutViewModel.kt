package com.vsv.navigation3example.presentation.square_layout

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SquareLayoutViewModel: ViewModel() {

    private val _state = MutableStateFlow(SquareLayoutState())
    val state = _state.asStateFlow()

    fun onAction(action: SquareLayoutAction) {
        when(action) {
            SquareLayoutAction.OnCircleClick -> {
                _state.update {
                    it.copy(
                        currentSquareId = null,
                        currentDetailPane = DetailPane.CIRCLE
                    )
                }
            }
            is SquareLayoutAction.OnSquareClick -> {
                _state.update {
                    it.copy(
                        currentSquareId = action.id,
                        currentDetailPane = DetailPane.SQUARE
                    )
                }
            }
        }
    }
}