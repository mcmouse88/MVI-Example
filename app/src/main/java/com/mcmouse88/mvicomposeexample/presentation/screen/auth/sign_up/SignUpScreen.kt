package com.mcmouse88.mvicomposeexample.presentation.screen.auth.sign_up

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mcmouse88.mvicomposeexample.R
import com.mcmouse88.mvicomposeexample.domain.model.UserModel
import com.mcmouse88.mvicomposeexample.presentation.items.ErrorItem
import com.mcmouse88.mvicomposeexample.presentation.items.LoadingItem
import com.mcmouse88.mvicomposeexample.presentation.navigation.Screens

@Composable
fun SignUpScreen(navController: NavHostController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var about by remember { mutableStateOf("") }

    val viewModel = hiltViewModel<SignUpViewModel>()
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            LoadingItem()
        }
        state.isSuccess -> {
            viewModel.sendEvent(SignUpScreenEvent.Default)
            navController.navigate(Screens.AuthScreenType.route)
        }
        state.error != null -> {
            ErrorItem(message = state.error) {
                viewModel.sendEvent(SignUpScreenEvent.Default)
            }
        }
        else -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.registration),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                TextField(
                    value = login,
                    onValueChange = { login = it },
                    label = { Text(text = stringResource(R.string.login)) },
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = stringResource(R.string.password)) },
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text(text = stringResource(R.string.first_name)) },
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text(text = stringResource(R.string.last_name)) },
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                TextField(
                    value = about,
                    onValueChange = { about = it },
                    label = { Text(text = stringResource(R.string.about)) },
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Button(
                    onClick = {
                        val userLogin = login.lowercase().trim()
                        val userPassword = password.trim()
                        val userFirstName = firstName.trim()
                        val userLastName = lastName.trim()

                        if (userLogin.isNotEmpty() && userPassword.isNotEmpty()
                            && userFirstName.isNotEmpty() && userLastName.isNotEmpty()) {
                            val user = UserModel(
                                login = login.lowercase().trim(),
                                password = password.trim(),
                                firstName = firstName.trim(),
                                lastName = lastName.trim(),
                                about = about
                            )

                            viewModel.sendEvent(
                                event = SignUpScreenEvent.RegisterEvent(
                                    userModel = user
                                )
                            )
                        }
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(text = stringResource(R.string.sign_up))
                }

                Text(
                    text = stringResource(R.string.log_in),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate(Screens.AuthScreenType.route) }
                )
            }
        }
    }
}