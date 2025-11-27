package com.vsv.navigation3example.presentation.square_layout

data class SquareLayoutState(
    val currentSquareId: Int? = null,
    val currentDetailPane: DetailPane = DetailPane.CIRCLE
)

enum class DetailPane{
    CIRCLE, SQUARE
}