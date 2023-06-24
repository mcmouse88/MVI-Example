package com.mcmouse88.mvicomposeexample.domain.repository

import com.mcmouse88.mvicomposeexample.domain.model.NetworkResult
import com.mcmouse88.mvicomposeexample.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun sighUp(user: UserModel): Flow<NetworkResult<Boolean>>

    suspend fun login(email: String, password: String): Flow<NetworkResult<Boolean>>
}