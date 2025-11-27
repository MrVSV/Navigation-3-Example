package com.vsv.navigation3example.presentation.navigation.entries

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.vsv.navigation3example.presentation.navigation.destination.HomeDestination
import com.vsv.navigation3example.presentation.square_layout.SquaresLayout

fun EntryProviderScope<NavKey>.homeEntries() {
    entry<HomeDestination.Squares> {
        SquaresLayout()
    }
}