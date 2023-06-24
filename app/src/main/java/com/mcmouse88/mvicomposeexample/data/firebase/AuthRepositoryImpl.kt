package com.mcmouse88.mvicomposeexample.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mcmouse88.mvicomposeexample.domain.model.NetworkResult
import com.mcmouse88.mvicomposeexample.domain.model.UserModel
import com.mcmouse88.mvicomposeexample.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("PrivatePropertyName")
@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    private val TAG = AuthRepositoryImpl::class.simpleName

    override suspend fun sighUp(user: UserModel): Flow<NetworkResult<Boolean>> {
        return flow {
            var isSuccess = false
            emit(NetworkResult.Loading())
            try {
                firebaseAuth.createUserWithEmailAndPassword(user.login, user.password)
                    .addOnCompleteListener { task ->
                        isSuccess = if (task.isSuccessful) {
                            Log.e(TAG, "sighUp: Success")
                            val firebaseUser = firebaseAuth.currentUser
                            if (firebaseUser != null) {
                                user.userId = firebaseUser.uid
                                firestore
                                    .collection(USERS)
                                    .document(firebaseUser.uid)
                                    .set(user)
                            }
                            firebaseUser != null
                        } else {
                            Log.e(TAG, "sighUp: Failure", task.exception)
                            false
                        }
                    }.await()
                if (isSuccess) {
                    emit(NetworkResult.Success(true))
                } else {
                    emit(NetworkResult.Error("Oops, something went wrong"))
                }
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.localizedMessage ?: "Oops, something went wrong"))
            }
        }
    }

    override suspend fun login(email: String, password: String): Flow<NetworkResult<Boolean>> {
        return flow {
            var isSuccess = false
            emit(NetworkResult.Loading())
            try {

                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        isSuccess = if (task.isSuccessful) {
                            Log.e(TAG, "login: Success!")
                            firebaseAuth.currentUser != null
                        } else {
                            Log.e(TAG, "login: Failure!", task.exception)
                            false
                        }
                    }.await()

                if (isSuccess) {
                    emit(NetworkResult.Success(true))
                } else {
                    emit(NetworkResult.Error("Oops, something went wrong"))
                }
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.localizedMessage ?: "Oops, something went wrong"))
            }
        }
    }

    private companion object {
        private const val USERS = "users"
    }
}