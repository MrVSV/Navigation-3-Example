package com.vsv.navigation3example.presentation.square_detail_screen

import com.vsv.navigation3example.domain.ColoredSquare

data class SquareDetailScreenState(
    val currentSquare: ColoredSquare? = null,
    val otherSquares: List<ColoredSquare> = emptyList()
)
