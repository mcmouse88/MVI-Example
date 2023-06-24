package com.mcmouse88.mvicomposeexample.presentation.screen.auth.login

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
import com.mcmouse88.mvicomposeexample.presentation.items.ErrorItem
import com.mcmouse88.mvicomposeexample.presentation.items.LoadingItem
import com.mcmouse88.mvicomposeexample.presentation.navigation.Screens

@Composable
fun AuthScreen(navController: NavHostController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel = hiltViewModel<AuthViewModel>()
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            LoadingItem()
        }
        state.isSuccess -> {
            viewModel.sendEvent(AuthScreenEvent.Default)
            navController.navigate(Screens.MainScreenType.route)
        }
        state.error != null -> {
            ErrorItem(message = state.error) {
                viewModel.sendEvent(AuthScreenEvent.Default)
            }
        }
        else -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.authorization),
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
                
                Button(
                    onClick = {
                        viewModel.sendEvent(
                            event = AuthScreenEvent.AuthorizationEvent(
                                login = login.lowercase().trim(),
                                password = password.trim()
                            )
                        )
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(text = stringResource(R.string.log_in))
                }
                
                Text(
                    text = stringResource(R.string.registration),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate(Screens.RegisterScreenType.route) }
                )
            }
        }
    }
}