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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.yuanzix.revitalize.data.repository.DataStoreRepository
import dev.yuanzix.revitalize.navigation.BaseScreenState
import dev.yuanzix.revitalize.ui.screens.main.MainScreen
import dev.yuanzix.revitalize.ui.screens.loading.LoadingScreen
import dev.yuanzix.revitalize.ui.theme.ReVitalizeTheme

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
    val context = LocalContext.current
    val dataStoreRepository = remember { DataStoreRepository(context) }
    val credentialsFlow by remember { mutableStateOf(dataStoreRepository.readUsernameAndPassword) }
    var screenState by remember { mutableStateOf(BaseScreenState.Loading) }

    ReVitalizeTheme {
        Crossfade(targetState = screenState, label = "") { state ->
            when (state) {
                BaseScreenState.Loading -> {
                    LoadingScreen()
                }

                BaseScreenState.Login -> {
                    LoginScreen(loginViewModel = viewModel(), onLogin = {
                        screenState = BaseScreenState.Home
                    })
                }

                BaseScreenState.Home -> {
                    MainScreen()
                }
            }
        }

        LaunchedEffect(key1 = credentialsFlow) {
            credentialsFlow.collect { userCredentials ->
                screenState = if (userCredentials.username.isEmpty()) BaseScreenState.Login else BaseScreenState.Home
            }
        }
    }
}