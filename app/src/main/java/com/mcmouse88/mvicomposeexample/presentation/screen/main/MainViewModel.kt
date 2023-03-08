package com.mcmouse88.mvicomposeexample.presentation.screen.main

import androidx.lifecycle.viewModelScope
import com.mcmouse88.mvicomposeexample.domain.usecase.LoadNotesUseCase
import com.mcmouse88.mvicomposeexample.presentation.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadNotesUseCase: LoadNotesUseCase
) : BaseViewModel<MainScreenState, MainScreenEvent>() {

    private val reducer = MainScreenReducer(
        initial = MainScreenState.initial(),
        useCase = loadNotesUseCase,
        scope = viewModelScope
    )

    override val state: StateFlow<MainScreenState>
        get() = reducer.state

    init {
        sendEvent(MainScreenEvent.LoadingData)
    }

    fun sendEvent(event: MainScreenEvent) {
        reducer.sendEvent(event)
    }
}