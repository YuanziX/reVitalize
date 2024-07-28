package dev.yuanzix.revitalize.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data object AttendanceScreen : Screen()

    @Serializable
    data object PerformanceScreen : Screen()

    @Serializable
    data object OthersScreen : Screen()
}