package com.vsv.navigation3example.presentation.navigation.entries

import androidx.navigation3.runtime.entryProvider
import com.vsv.navigation3example.presentation.navigation.util.Navigator

fun entryProvider(navigator: Navigator) = entryProvider {
    homeEntries()
    searchEntries()
    menuEntries()
}