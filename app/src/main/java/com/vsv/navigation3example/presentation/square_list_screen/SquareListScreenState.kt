package com.vsv.navigation3example.presentation.square_list_screen

import com.vsv.navigation3example.domain.ColoredSquare

data class SquareListScreenState(
    val squares: List<ColoredSquare> = emptyList()
)
