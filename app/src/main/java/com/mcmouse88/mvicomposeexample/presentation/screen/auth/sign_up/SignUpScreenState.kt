package com.mcmouse88.mvicomposeexample.presentation.screen.auth.sign_up

import com.mcmouse88.mvicomposeexample.presentation.screen.base.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class SignUpScreenState(
    val isLoading: Boolean,
    val isSuccess: Boolean,
    val error: String? = null
) : UiState {

    companion object {
        fun initial(): SignUpScreenState = SignUpScreenState(
            isLoading = false,
            isSuccess = false,
            error = null
        )
    }
}