package com.vsv.navigation3example.domain

object SquareStorage {

    private val coloredSquares = listOf(
        ColoredSquare(id = 1, color = 0xFFB00020), // Темно-красный
        ColoredSquare(id = 2, color = 0xFF3700B3), // Темно-фиолетовый
        ColoredSquare(id = 3, color = 0xFF03DAC6), // Бирюзовый
        ColoredSquare(id = 4, color = 0xFF01875F), // Темно-зеленый
        ColoredSquare(id = 5, color = 0xFFFCC203), // Янтарный/золотой
        ColoredSquare(id = 6, color = 0xFFF57C00), // Темно-оранжевый
        ColoredSquare(id = 7, color = 0xFFD81B60), // Розовый
        ColoredSquare(id = 8, color = 0xFF5D4037), // Темно-коричневый
        ColoredSquare(id = 9, color = 0xFF424242), // Темно-серый
        ColoredSquare(id = 10, color = 0xFF1976D2) // Темно-синий
    )

    fun getAllSquares() = coloredSquares

    fun getSquareById(id: Int) = coloredSquares.first { it.id == id }

    fun getOtherSquares(id: Int) = coloredSquares.filter { it.id != id }
}