package com.mcmouse88.mvicomposeexample.presentation.screen.auth.login

import com.mcmouse88.mvicomposeexample.presentation.screen.base.UiEvent
import javax.annotation.concurrent.Immutable

@Immutable
sealed class AuthScreenEvent : UiEvent {

    object Default: AuthScreenEvent()
    object LoadingData: AuthScreenEvent()
    data class AuthorizationEvent(val login: String, val password: String): AuthScreenEvent()
    data class ShowError(val message: String?): AuthScreenEvent()
}