package com.mcmouse88.mvicomposeexample.presentation.screen.main

import com.mcmouse88.mvicomposeexample.domain.usecase.LoadNotesUseCase
import com.mcmouse88.mvicomposeexample.presentation.screen.base.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainScreenReducer(
    initial: MainScreenState,
    private val useCase: LoadNotesUseCase,
    private val scope: CoroutineScope
) : Reducer<MainScreenState, MainScreenEvent>(initial) {

    override fun reduce(oldState: MainScreenState, event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.ShowData -> {
                setState(oldState.copy(isLoading = false, data = event.data))
            }
            is MainScreenEvent.LoadingData -> {
                scope.launch {
                    setState(oldState.copy(isLoading = true, data = emptyList()))
                    try {
                        useCase.execute().let { data ->
                            if (data.isNotEmpty()) {
                                sendEvent(MainScreenEvent.ShowData(data = data))
                            } else {
                                sendEvent(MainScreenEvent.ShowError(message = "Data is Empty"))
                            }
                        }
                    } catch (e: Exception) {
                        sendEvent(MainScreenEvent.ShowError(message = e.message ?: "Error!!!"))
                    }
                }
            }
            is MainScreenEvent.ShowError -> {
                setState(
                    oldState.copy(
                        isLoading = false,
                        data = emptyList(),
                        error = event.message
                    )
                )
            }
        }
    }
}