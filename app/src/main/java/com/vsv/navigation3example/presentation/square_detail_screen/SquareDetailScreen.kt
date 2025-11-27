package com.vsv.navigation3example.presentation.square_detail_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vsv.navigation3example.presentation.components.SquareItem

@Composable
fun SquareDetailScreenRoot(
    onBack: () -> Unit,
    shouldHandleOnBack: Boolean,
    onSquareClick: (Int) -> Unit,
    viewModel: SquareDetailScreenViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BackHandler(shouldHandleOnBack) {
        onBack()
    }

    SquareDetailScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is SquareDetailScreenAction.OnOtherSquareClick -> onSquareClick(action.id)
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun SquareDetailScreen(
    state: SquareDetailScreenState,
    onAction: (SquareDetailScreenAction) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.currentSquare != null) {
            item(
                span = {
                    GridItemSpan(3)
                }
            ) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    SquareItem(
                        color = state.currentSquare.color,
                        selected = false,
                        onClick = {},
                        modifier = Modifier.weight(2f)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        if (state.otherSquares.isNotEmpty()) {
            item(
                span = {
                    GridItemSpan(3)
                }
            ) {
                Text(
                    text = "OTHER SQUARES",
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
            items(state.otherSquares) { square ->
                SquareItem(
                    color = square.color,
                    selected = false,
                    onClick = {
                        onAction(SquareDetailScreenAction.OnOtherSquareClick(square.id))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}