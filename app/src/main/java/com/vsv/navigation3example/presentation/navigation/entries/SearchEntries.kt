package com.vsv.navigation3example.presentation.navigation.entries

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.vsv.navigation3example.presentation.components.Counter
import com.vsv.navigation3example.presentation.navigation.destination.SearchDestination
import com.vsv.navigation3example.presentation.util.CounterViewModel
import org.koin.androidx.compose.koinViewModel

fun EntryProviderScope<NavKey>.searchEntries() {
    entry<SearchDestination.Search> {
        val searchViewModel: CounterViewModel = koinViewModel()
        val count by searchViewModel.count.collectAsStateWithLifecycle()
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {

            Counter(
                currentCount = count,
                onClick = { searchViewModel.increment() }
            )
        }
    }
}