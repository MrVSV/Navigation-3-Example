package com.vsv.navigation3example.presentation.square_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vsv.navigation3example.presentation.components.SquareItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun SquareListScreenRoot(
    selectedSquareId: Int?,
    onSquareClick: (Int) -> Unit,
    onCircleClick: () -> Unit,
    viewModel: SquareListScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SquareListScreen(
        selectedSquareId = selectedSquareId,
        state = state,
        onAction = { action ->
            when (action) {
                is SquareListScreenAction.OnSquareClick -> onSquareClick(action.id)
                SquareListScreenAction.OnCircleClick -> onCircleClick()
            }
            viewModel.onAction(action)
        }
    )

}

@Composable
fun SquareListScreen(
    selectedSquareId: Int?,
    state: SquareListScreenState,
    onAction: (SquareListScreenAction) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .background(Color.Cyan)
                    .clickable {
                        onAction(SquareListScreenAction.OnCircleClick)
                    }
            )
        }
        items(state.squares) { square ->
            SquareItem(
                color = square.color,
                selected = square.id == selectedSquareId,
                onClick = {
                    onAction(SquareListScreenAction.OnSquareClick(square.id))
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}