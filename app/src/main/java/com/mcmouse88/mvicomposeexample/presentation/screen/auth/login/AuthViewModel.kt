package com.mcmouse88.mvicomposeexample.presentation.screen.auth.login

import androidx.lifecycle.viewModelScope
import com.mcmouse88.mvicomposeexample.domain.usecase.AuthUseCase
import com.mcmouse88.mvicomposeexample.presentation.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    authUseCase: AuthUseCase
) : BaseViewModel<AuthScreenState, AuthScreenEvent>() {

    private val reducer = AuthScreenReducer(
        initial = AuthScreenState.initial(),
        authUseCase = authUseCase,
        scope = viewModelScope
    )

    override val state: StateFlow<AuthScreenState>
        get() = reducer.state

    fun sendEvent(event: AuthScreenEvent) {
        reducer.sendEvent(event)
    }
}