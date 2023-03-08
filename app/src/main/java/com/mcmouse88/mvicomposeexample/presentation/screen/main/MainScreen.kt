package com.mcmouse88.mvicomposeexample.presentation.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mcmouse88.mvicomposeexample.presentation.items.ErrorItem
import com.mcmouse88.mvicomposeexample.presentation.items.LoadingItem
import com.mcmouse88.mvicomposeexample.presentation.items.MainScreenContent

@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            LoadingItem()
        }
        state.data.isNotEmpty() -> {
            MainScreenContent(data = state.data)
        }
        state.error != null -> {
            ErrorItem(message = state.error) {
                viewModel.sendEvent(MainScreenEvent.LoadingData)
            }
        }
    }
}