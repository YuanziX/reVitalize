package dev.yuanzix.revitalize

import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.yuanzix.revitalize.navigation.BaseScreenState
import dev.yuanzix.revitalize.ui.screens.loading.LoadingScreen
import dev.yuanzix.revitalize.ui.screens.main.MainScreen
import dev.yuanzix.revitalize.ui.theme.ReVitalizeTheme
import dev.yuanzix.revitalize.ui.viewmodels.loginViewModel.LoginViewModel
import dev.yuanzix.revitalize.ui.viewmodels.mainViewModel.MainViewModel
import dev.yuanzix.revitalize.util.checkDateAndTriggerFunction

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val mainViewModel: MainViewModel = hiltViewModel()
    var screenState by remember { mutableStateOf(BaseScreenState.Loading) }

    ReVitalizeTheme {
        Crossfade(targetState = screenState, label = "") { state ->
            when (state) {
                BaseScreenState.Loading -> {
                    LoadingScreen()
                }

                BaseScreenState.Login -> {
                    LoginScreen(
                        onLogin = {
                            screenState = BaseScreenState.Home
                        }, loginViewModel = loginViewModel
                    )
                }

                BaseScreenState.Home -> {
                    if (mainViewModel.credentialsLoaded.value) {
                        checkDateAndTriggerFunction(
                            loginViewModel.dataStoreRepository
                        ) { mainViewModel.updateAllData() }
                    }
                    MainScreen(
                        mainViewModel = mainViewModel
                    )
                }
            }
        }

        LaunchedEffect(key1 = loginViewModel.credentials) {
            loginViewModel.credentials.collect { userCredentials ->
                screenState =
                    if (userCredentials.username.isEmpty()) BaseScreenState.Login else BaseScreenState.Home
            }
        }
    }
}
