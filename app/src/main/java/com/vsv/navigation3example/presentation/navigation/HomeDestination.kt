package com.vsv.navigation3example.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeDestination: Destination {

    @Serializable
    data object Squares: HomeDestination

}