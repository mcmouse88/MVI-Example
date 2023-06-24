package com.mcmouse88.mvicomposeexample.presentation.screen.auth.sign_up

import androidx.lifecycle.viewModelScope
import com.mcmouse88.mvicomposeexample.domain.usecase.AuthUseCase
import com.mcmouse88.mvicomposeexample.presentation.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    authUseCase: AuthUseCase
) : BaseViewModel<SignUpScreenState, SignUpScreenEvent>() {

    private val reducer = SignUpScreenReducer(
        initial = SignUpScreenState.initial(),
        authUseCase = authUseCase,
        scope = viewModelScope
    )

    override val state: StateFlow<SignUpScreenState>
        get() = reducer.state

    fun sendEvent(event: SignUpScreenEvent) {
        reducer.sendEvent(event)
    }
}