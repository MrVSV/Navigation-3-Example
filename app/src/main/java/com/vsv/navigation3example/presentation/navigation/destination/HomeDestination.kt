package com.vsv.navigation3example.presentation.navigation.destination

import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeDestination : Destination {

    @Serializable
    data object SquareList : HomeDestination

    @Serializable
    data class SquareDetails(val squareId: Int) : HomeDestination

    @Serializable
    data object CircleDetails : HomeDestination

}