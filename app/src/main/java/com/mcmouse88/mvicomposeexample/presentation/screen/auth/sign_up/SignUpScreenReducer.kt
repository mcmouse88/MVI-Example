package com.mcmouse88.mvicomposeexample.presentation.screen.auth.sign_up

import com.mcmouse88.mvicomposeexample.domain.usecase.AuthUseCase
import com.mcmouse88.mvicomposeexample.presentation.screen.base.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SignUpScreenReducer(
    initial: SignUpScreenState,
    val authUseCase: AuthUseCase,
    val scope: CoroutineScope
) : Reducer<SignUpScreenState, SignUpScreenEvent>(initial) {

    override fun reduce(oldState: SignUpScreenState, event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.Default -> setState(oldState.copy(
                isLoading = false,
                isSuccess = false,
                error = null
            ))

            is SignUpScreenEvent.LoadingData -> setState(oldState.copy(
                isLoading = true,
                isSuccess = false
            ))

            is SignUpScreenEvent.RegisterEvent -> {
                sendEvent(SignUpScreenEvent.LoadingData)
                scope.launch {
                    try {
                        authUseCase.registerNewUser(event.userModel).collect {
                            if (it.data != null) {
                                setState(oldState.copy(isLoading = false, isSuccess = true))
                            } else if (it.message != null) {
                                sendEvent(SignUpScreenEvent.ShowError(it.message))
                            } else {
                                sendEvent(SignUpScreenEvent.LoadingData)
                            }
                        }
                    } catch (e: Exception) {
                        sendEvent(SignUpScreenEvent.ShowError(e.message ?: "Oops, something went wrong"))
                    }
                }
            }

            is SignUpScreenEvent.ShowError -> {
                setState(oldState.copy(isLoading = false, isSuccess = false, error = event.message))
            }
        }
    }
}