package com.vsv.navigation3example.presentation.navigation.util

import androidx.navigation3.runtime.NavKey

class Navigator(
    private val state: NavigationState
) {
    fun navigate(route: NavKey, topLevelRoute: NavKey = state.topLevelRoute) {
        when {
            route in state.backStacks.keys -> {
                state.topLevelRoute = route
            }

            topLevelRoute != state.topLevelRoute -> {
                if (topLevelRoute !in state.backStacks.keys) return
                state.topLevelRoute = topLevelRoute
                state.backStacks[topLevelRoute]?.add(route)
            }

            else -> {
                state.backStacks[state.topLevelRoute]?.add(route)
            }
        }
    }

    fun goBack() {
        val currentStack = state.backStacks[state.topLevelRoute]
            ?: error("Stack for ${state.topLevelRoute} not found")
        val currentRoute = currentStack.last()
        if (currentRoute == state.topLevelRoute) {
            state.topLevelRoute = state.startRoute
        } else {
            currentStack.removeLastOrNull()
        }
    }

    fun resetStack() {
        val currentStack = state.backStacks[state.topLevelRoute]
            ?: error("Stack for ${state.topLevelRoute} not found")
        currentStack.clear()
        currentStack.add(state.topLevelRoute)
    }
}