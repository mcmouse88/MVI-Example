package com.mcmouse88.mvicomposeexample.presentation.screen.auth.sign_up

import com.mcmouse88.mvicomposeexample.domain.model.UserModel
import com.mcmouse88.mvicomposeexample.presentation.screen.base.UiEvent
import javax.annotation.concurrent.Immutable

@Immutable
sealed class SignUpScreenEvent : UiEvent {

    object Default: SignUpScreenEvent()
    object LoadingData: SignUpScreenEvent()
    data class RegisterEvent(val userModel: UserModel): SignUpScreenEvent()
    data class ShowError(val message: String?): SignUpScreenEvent()
}