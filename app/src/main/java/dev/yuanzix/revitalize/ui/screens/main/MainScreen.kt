package dev.yuanzix.revitalize.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dev.yuanzix.revitalize.navigation.BottomNavigationBar
import dev.yuanzix.revitalize.navigation.NavigationScreens
import dev.yuanzix.revitalize.ui.viewmodels.mainViewModel.MainViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()

    val exceptionMessage by mainViewModel.exceptionMessage

    if (exceptionMessage != null) {
        ExceptionDialog(
            message = exceptionMessage ?: "",
            onDismiss = { mainViewModel.clearException() }
        )
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavigationScreens(navController = navController)
        }
    }
}

@Composable
fun ExceptionDialog(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Error") },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}