package com.vsv.navigation3example.presentation.activity

sealed interface MainActivityAction {
    data class OnBottomTabClick(val tab: BottomTab): MainActivityAction
}