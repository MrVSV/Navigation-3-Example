package com.vsv.navigation3example.presentation.square_layout

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.rememberNavBackStack
import com.vsv.navigation3example.presentation.square_detail_navigation.SquareDetailDestination
import com.vsv.navigation3example.presentation.square_detail_navigation.SquareDetailNavigation
import com.vsv.navigation3example.presentation.square_list_screen.SquareListScreenRoot
import com.vsv.navigation3example.presentation.util.createPaneScaffoldDirective
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SquaresLayout(
    viewModel: SquareLayoutViewModel = koinViewModel()
) {
    val sharedState by viewModel.state.collectAsStateWithLifecycle()
    val scaffoldDirective = createPaneScaffoldDirective()
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator(
        scaffoldDirective = scaffoldDirective
    )
    val scope = rememberCoroutineScope()

    val detailPane = scaffoldNavigator.scaffoldValue[ListDetailPaneScaffoldRole.Detail]

    LaunchedEffect(detailPane, sharedState.currentSquareId) {
        Log.d(TAG, "SquaresLayout: ${PaneAdaptedValue.Hidden}")
        if (detailPane == PaneAdaptedValue.Hidden && sharedState.currentSquareId != null) {
            viewModel.onAction(SquareLayoutAction.OnSquareClick(null))
        }
    }

    val squareDetailBackStack = rememberNavBackStack(SquareDetailDestination.SquareDetail(null))

    BackHandler(scaffoldNavigator.canNavigateBack()) {
        scope.launch { scaffoldNavigator.navigateBack() }
    }

    ListDetailPaneScaffold(
        directive = scaffoldDirective,
        value = scaffoldNavigator.scaffoldValue,
        listPane = {
            AnimatedPane(
                modifier = Modifier.preferredWidth(0.5f)
            ) {
                SquareListScreenRoot(
                    selectedSquareId = sharedState.currentSquareId,
                    onSquareClick = {
                        viewModel.onAction(SquareLayoutAction.OnSquareClick(it))
                        squareDetailBackStack.clear()
                        squareDetailBackStack.add(SquareDetailDestination.SquareDetail(it))
                        scope.launch {
                            scaffoldNavigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                        }
                    },
                    onCircleClick = {
                        viewModel.onAction(SquareLayoutAction.OnCircleClick)
                        scope.launch {
                            scaffoldNavigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane(
                modifier = Modifier.preferredWidth(0.5f)
            ) {
                when (sharedState.currentDetailPane) {
                    DetailPane.CIRCLE -> {
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

                    DetailPane.SQUARE -> {
                        SquareDetailNavigation(
                            backStack = squareDetailBackStack,
                            onBack = {
                                scope.launch {
                                    val previousIndex = squareDetailBackStack.lastIndex - 1
                                    if (previousIndex >= 0) {
                                        val previousSquare =
                                            squareDetailBackStack[previousIndex] as? SquareDetailDestination.SquareDetail
                                        if (previousSquare == null) {
                                            viewModel.onAction(SquareLayoutAction.OnSquareClick(null))
                                        } else {
                                            viewModel.onAction(SquareLayoutAction.OnSquareClick(previousSquare.squareId))
                                        }
                                        squareDetailBackStack.removeLastOrNull()
                                    } else if (scaffoldNavigator.canNavigateBack()) {
                                        scaffoldNavigator.navigateBack()
                                    }
                                }
                            },
                            onSquareClick = {
                                viewModel.onAction(SquareLayoutAction.OnSquareClick(it))
                                squareDetailBackStack.add(SquareDetailDestination.SquareDetail(it))
                            }
                        )
                    }
                }
            }
        },
    )
}