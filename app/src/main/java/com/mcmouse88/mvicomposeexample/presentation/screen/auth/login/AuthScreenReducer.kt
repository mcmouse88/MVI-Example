package com.mcmouse88.mvicomposeexample.presentation.screen.auth.login

import com.mcmouse88.mvicomposeexample.domain.usecase.AuthUseCase
import com.mcmouse88.mvicomposeexample.presentation.screen.base.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AuthScreenReducer(
    initial: AuthScreenState,
    val authUseCase: AuthUseCase,
    val scope: CoroutineScope
) : Reducer<AuthScreenState, AuthScreenEvent>(initial) {

    override fun reduce(oldState: AuthScreenState, event: AuthScreenEvent) {
        when (event) {
            is AuthScreenEvent.Default -> setState(oldState.copy(
                isLoading = false,
                isSuccess = false,
                error = null
            ))

            is AuthScreenEvent.LoadingData -> setState(oldState.copy(
                isLoading = true,
                isSuccess = false
            ))

            is AuthScreenEvent.AuthorizationEvent -> {
                sendEvent(AuthScreenEvent.LoadingData)
                scope.launch {
                    try {
                        authUseCase.loginUser(email = event.login, password = event.password).collect {
                            if (it.data != null) {
                                setState(oldState.copy(isLoading = false, isSuccess = true))
                            } else if (it.message != null) {
                                sendEvent(AuthScreenEvent.ShowError(it.message))
                            } else {
                                sendEvent(AuthScreenEvent.LoadingData)
                            }
                        }
                    } catch (e: Exception) {
                        sendEvent(AuthScreenEvent.ShowError(e.message ?: "Oops, something went wrong"))
                    }
                }
            }

            is AuthScreenEvent.ShowError -> {
                setState(oldState.copy(isLoading = false, isSuccess = false, error = event.message))
            }
        }
    }
}