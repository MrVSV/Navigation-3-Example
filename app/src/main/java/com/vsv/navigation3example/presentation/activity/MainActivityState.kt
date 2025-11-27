package com.vsv.navigation3example.presentation.activity

import androidx.annotation.DrawableRes
import com.vsv.navigation3example.R

data class MainActivityState(
    val currentTab: BottomTab = BottomTab.HOME
)

enum class BottomTab(@DrawableRes val icon: Int) {
    HOME(R.drawable.ic_home),
    SEARCH(R.drawable.ic_search),
    MENU(R.drawable.ic_menu)
}
