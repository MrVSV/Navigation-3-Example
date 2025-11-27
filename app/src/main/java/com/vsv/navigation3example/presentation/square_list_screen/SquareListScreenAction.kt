package com.vsv.navigation3example.presentation.square_list_screen

sealed interface SquareListScreenAction {
    data class OnSquareClick(val id: Int): SquareListScreenAction
    data object OnCircleClick: SquareListScreenAction
}