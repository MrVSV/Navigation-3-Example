package com.vsv.navigation3example.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.vsv.navigation3example.presentation.navigation.destination.HomeDestination
import com.vsv.navigation3example.presentation.navigation.destination.MenuDestination
import com.vsv.navigation3example.presentation.navigation.destination.SearchDestination
import com.vsv.navigation3example.presentation.navigation.entries.entryProvider
import com.vsv.navigation3example.presentation.navigation.util.Navigator
import com.vsv.navigation3example.presentation.navigation.util.rememberNavigationState
import com.vsv.navigation3example.presentation.navigation.util.rememberTwoPaneSceneStrategy
import com.vsv.navigation3example.presentation.navigation.util.toEntries
import com.vsv.navigation3example.presentation.ui.theme.Navigation3ExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val topLevelDestinations = mapOf(
                HomeDestination.SquareList to BottomTab.HOME,
                SearchDestination.Search to BottomTab.SEARCH,
                MenuDestination.Menu to BottomTab.MENU
            )
            val navigationState = rememberNavigationState(
                startRoute = HomeDestination.SquareList,
                topLevelRoutes = topLevelDestinations.keys
            )

            val navigator = remember { Navigator(navigationState) }

            val sceneStrategy = rememberTwoPaneSceneStrategy<NavKey>()

            Navigation3ExampleTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            topLevelDestinations.forEach { (destination, tab) ->
                                val selected = destination == navigationState.topLevelRoute
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = {
                                        if (selected) {
                                            navigator.resetStack()
                                        } else {
                                            navigator.navigate(destination)
                                        }
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
                        NavDisplay(
                            entries = navigationState.toEntries(entryProvider(navigator)),
                            sceneStrategy = sceneStrategy,
                            onBack = { navigator.goBack() },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}
