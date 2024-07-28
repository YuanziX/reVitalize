package dev.yuanzix.revitalize.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.yuanzix.revitalize.ui.screens.attendance.AttendanceScreen
import dev.yuanzix.revitalize.ui.screens.home.HomeScreen
import dev.yuanzix.revitalize.ui.screens.others.OthersScreen
import dev.yuanzix.revitalize.ui.screens.performance.PerformanceScreen

@Composable
fun NavigationScreens(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen) {
        composable<Screen.HomeScreen> { HomeScreen() }
        composable<Screen.AttendanceScreen> { AttendanceScreen() }
        composable<Screen.PerformanceScreen> { PerformanceScreen() }
        composable<Screen.OthersScreen> { OthersScreen() }
    }
}