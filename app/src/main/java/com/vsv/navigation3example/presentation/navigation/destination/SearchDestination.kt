package com.vsv.navigation3example.presentation.navigation.destination

import kotlinx.serialization.Serializable

@Serializable
sealed interface SearchDestination: Destination {

    @Serializable
    data object Search: SearchDestination
}