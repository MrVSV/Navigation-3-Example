package com.vsv.navigation3example.presentation.navigation.entries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.vsv.navigation3example.presentation.navigation.destination.HomeDestination
import com.vsv.navigation3example.presentation.navigation.util.Navigator
import com.vsv.navigation3example.presentation.navigation.util.TwoPaneScene
import com.vsv.navigation3example.presentation.square_detail_screen.SquareDetailScreenRoot
import com.vsv.navigation3example.presentation.square_detail_screen.SquareDetailScreenViewModel
import com.vsv.navigation3example.presentation.square_list_screen.SquareListScreenRoot
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parameterSetOf

fun EntryProviderScope<NavKey>.homeEntries(
    navigator: Navigator
) {
    entry<HomeDestination.SquareList>(
        metadata = TwoPaneScene.listPane()
    ) {
        SquareListScreenRoot(
            onCircleClick = {
                navigator.navigate(HomeDestination.CircleDetails)
            },
            onSquareClick = {
                navigator.navigate(HomeDestination.SquareDetails(it))
            },
            selectedSquareId = null,
        )
    }
    entry<HomeDestination.CircleDetails>(
        metadata = TwoPaneScene.detailPane()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .background(Color.Cyan)
            )
        }
    }
    entry<HomeDestination.SquareDetails>(
        metadata = TwoPaneScene.detailPane()
    ) {
        SquareDetailScreenRoot(
            onBack = { navigator.goBack() },
            shouldHandleOnBack = false,
            onSquareClick = { navigator.navigate(HomeDestination.SquareDetails(it)) },
            viewModel = koinViewModel<SquareDetailScreenViewModel> {
                parameterSetOf(it.squareId)
            },
        )
    }
}