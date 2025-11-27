package com.vsv.navigation3example.presentation.square_layout

sealed interface SquareLayoutAction {
    data class OnSquareClick(val id: Int?): SquareLayoutAction
    data object OnCircleClick: SquareLayoutAction
}