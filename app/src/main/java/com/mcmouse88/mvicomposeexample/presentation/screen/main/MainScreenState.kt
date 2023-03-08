package com.mcmouse88.mvicomposeexample.presentation.screen.main

import com.mcmouse88.mvicomposeexample.domain.model.NoteModel
import com.mcmouse88.mvicomposeexample.presentation.screen.base.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class MainScreenState(
    val isLoading: Boolean,
    val data: List<NoteModel>,
    val error: String? = null
) : UiState {

    companion object {
        fun initial(): MainScreenState = MainScreenState(
            isLoading = true,
            data = emptyList(),
            error = null
        )
    }
}