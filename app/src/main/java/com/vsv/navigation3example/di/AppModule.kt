package com.vsv.navigation3example.di

import com.vsv.navigation3example.presentation.activity.MainActivityViewModel
import com.vsv.navigation3example.presentation.square_detail_screen.SquareDetailScreenViewModel
import com.vsv.navigation3example.presentation.square_layout.SquareLayoutViewModel
import com.vsv.navigation3example.presentation.square_list_screen.SquareListScreenViewModel
import com.vsv.navigation3example.presentation.util.CounterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::MainActivityViewModel)
    viewModelOf(::SquareLayoutViewModel)
    viewModelOf(::SquareListScreenViewModel)
    viewModelOf(::SquareDetailScreenViewModel)
    viewModelOf(::CounterViewModel)

}