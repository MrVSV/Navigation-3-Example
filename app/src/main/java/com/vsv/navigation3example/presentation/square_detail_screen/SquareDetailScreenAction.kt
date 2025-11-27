package com.vsv.navigation3example.presentation.square_detail_screen

sealed interface SquareDetailScreenAction {
    data class OnOtherSquareClick(val id: Int): SquareDetailScreenAction
}