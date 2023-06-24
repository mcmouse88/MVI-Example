package com.mcmouse88.mvicomposeexample.domain.usecase

import com.mcmouse88.mvicomposeexample.domain.model.NetworkResult
import com.mcmouse88.mvicomposeexample.domain.model.UserModel
import com.mcmouse88.mvicomposeexample.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun registerNewUser(userModel: UserModel): Flow<NetworkResult<Boolean>> {
        return repository.sighUp(userModel)
    }

    suspend fun loginUser(email: String, password: String): Flow<NetworkResult<Boolean>> {
        return repository.login(
            email = email,
            password = password
        )
    }
}