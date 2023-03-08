package com.mcmouse88.mvicomposeexample.domain.usecase

abstract class BaseUseCase<T> {

    abstract suspend fun execute(): T
}