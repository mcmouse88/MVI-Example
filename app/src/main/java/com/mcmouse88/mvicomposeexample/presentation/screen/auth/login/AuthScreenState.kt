package com.mcmouse88.mvicomposeexample.presentation.screen.auth.login

import com.mcmouse88.mvicomposeexample.presentation.screen.base.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class AuthScreenState(
    val isLoading: Boolean,
    val isSuccess: Boolean,
    val error: String? = null
) : UiState {

    companion object {
        fun initial(): AuthScreenState = AuthScreenState(
            isLoading = false,
            isSuccess = false,
            error = null
        )
    }
}