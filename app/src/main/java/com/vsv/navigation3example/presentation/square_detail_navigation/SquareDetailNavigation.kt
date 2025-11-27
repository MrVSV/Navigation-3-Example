package com.vsv.navigation3example.presentation.square_detail_navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.vsv.navigation3example.presentation.square_detail_screen.SquareDetailScreenRoot
import com.vsv.navigation3example.presentation.square_detail_screen.SquareDetailScreenViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SquareDetailNavigation(
    backStack: NavBackStack<NavKey>,
    onBack: () -> Unit,
    onSquareClick: (Int) -> Unit,
) {
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        onBack = {},
        entryProvider = entryProvider {
            entry<SquareDetailDestination.SquareDetail> {
                SquareDetailScreenRoot(
                    onBack = onBack,
                    shouldHandleOnBack = backStack.size > 1,
                    onSquareClick = onSquareClick,
                    viewModel = koinViewModel<SquareDetailScreenViewModel> {
                        parametersOf(it.squareId)
                    }
                )
            }
        }
    )
}