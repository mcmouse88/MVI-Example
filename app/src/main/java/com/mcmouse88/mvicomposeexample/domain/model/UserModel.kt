package com.mcmouse88.mvicomposeexample.domain.model

data class UserModel(
    var userId: String? = null,
    var login: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val about: String? = null
)
