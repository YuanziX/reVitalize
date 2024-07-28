package dev.yuanzix.revitalize.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dev.yuanzix.revitalize.navigation.BottomNavigationBar
import dev.yuanzix.revitalize.navigation.NavigationScreens

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BottomNavigationBar(navController = navController)}
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavigationScreens(navController = navController)
        }
    }
}