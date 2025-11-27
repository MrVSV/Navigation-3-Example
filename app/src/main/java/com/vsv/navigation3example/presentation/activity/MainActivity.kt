package com.vsv.navigation3example.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.vsv.navigation3example.presentation.components.Counter
import com.vsv.navigation3example.presentation.navigation.HomeDestination
import com.vsv.navigation3example.presentation.navigation.MenuDestination
import com.vsv.navigation3example.presentation.navigation.SearchDestination
import com.vsv.navigation3example.presentation.square_layout.SquaresLayout
import com.vsv.navigation3example.presentation.ui.theme.Navigation3ExampleTheme
import com.vsv.navigation3example.presentation.util.CounterViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel: MainActivityViewModel = koinViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            val homeBackStack = rememberNavBackStack(HomeDestination.Squares)
            val searchBackStack = rememberNavBackStack(SearchDestination.Search)
            val menuBackStack = rememberNavBackStack(MenuDestination.Menu)

            val currentBackStack = when (state.currentTab) {
                BottomTab.HOME -> homeBackStack
                BottomTab.SEARCH -> searchBackStack
                BottomTab.MENU -> menuBackStack
            }
            BackHandler(true) {
                when {
                    currentBackStack.size > 1 -> {
                        currentBackStack.removeLastOrNull()
                    }
                    state.currentTab != BottomTab.HOME -> {
                        viewModel.onAction(MainActivityAction.OnBottomTabClick(BottomTab.HOME))
                    }
                    else -> {
                        finish()
                    }
                }
            }
            Navigation3ExampleTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar() {
                            BottomTab.entries.forEach { tab ->
                                NavigationBarItem(
                                    selected = tab == state.currentTab,
                                    onClick = {
                                        viewModel.onAction(MainActivityAction.OnBottomTabClick(tab))
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(tab.icon),
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (state.currentTab) {
                            BottomTab.HOME -> {
                                NavDisplay(
                                    backStack = homeBackStack,
                                    entryDecorators = listOf(
                                        rememberSaveableStateHolderNavEntryDecorator(),
                                        rememberViewModelStoreNavEntryDecorator(),
                                    ),
                                    entryProvider = entryProvider {
                                        entry<HomeDestination.Squares> {
                                            SquaresLayout()
                                        }
                                    }
                                )
                            }

                            BottomTab.SEARCH -> {
                                NavDisplay(
                                    backStack = searchBackStack,
                                    entryDecorators = listOf(
                                        rememberSaveableStateHolderNavEntryDecorator(),
                                        rememberViewModelStoreNavEntryDecorator(),
                                    ),
                                    entryProvider = entryProvider {
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
                                )
                            }

                            BottomTab.MENU -> {
                                NavDisplay(
                                    backStack = menuBackStack,
                                    entryDecorators = listOf(
                                        rememberSaveableStateHolderNavEntryDecorator(),
                                        rememberViewModelStoreNavEntryDecorator(),
                                    ),
                                    entryProvider = entryProvider {
                                        entry<MenuDestination.Menu> {
                                            val menuViewModel: CounterViewModel = koinViewModel()
                                            val count by menuViewModel.count.collectAsStateWithLifecycle()
                                            Box(
                                                contentAlignment = Alignment.Center,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                            ) {
                                                Counter(
                                                    currentCount = count,
                                                    onClick = { menuViewModel.increment() }
                                                )
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
