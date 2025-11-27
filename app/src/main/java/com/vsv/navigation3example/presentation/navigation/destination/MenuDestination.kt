package com.vsv.navigation3example.presentation.navigation.destination

import kotlinx.serialization.Serializable

@Serializable
sealed interface MenuDestination: Destination {

    @Serializable
    data object Menu: MenuDestination

}