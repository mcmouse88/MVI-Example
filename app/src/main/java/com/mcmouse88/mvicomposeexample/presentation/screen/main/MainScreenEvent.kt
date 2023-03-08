package com.mcmouse88.mvicomposeexample.presentation.screen.main

import com.mcmouse88.mvicomposeexample.domain.model.NoteModel
import com.mcmouse88.mvicomposeexample.presentation.screen.base.UiEvent
import javax.annotation.concurrent.Immutable

@Immutable
sealed class MainScreenEvent : UiEvent {
    object LoadingData : MainScreenEvent()
    data class ShowData(val data: List<NoteModel>) : MainScreenEvent()
    data class ShowError(val message: String) : MainScreenEvent()
}