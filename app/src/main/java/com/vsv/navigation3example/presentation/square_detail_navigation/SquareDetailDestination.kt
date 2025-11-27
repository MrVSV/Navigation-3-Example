package com.vsv.navigation3example.presentation.square_detail_navigation

import com.vsv.navigation3example.presentation.navigation.destination.Destination
import kotlinx.serialization.Serializable

@Serializable
sealed interface SquareDetailDestination: Destination {

    @Serializable
    data class SquareDetail(val squareId: Int?): SquareDetailDestination
}