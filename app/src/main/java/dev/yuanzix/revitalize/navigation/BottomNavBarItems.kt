package dev.yuanzix.revitalize.navigation

import androidx.annotation.DrawableRes
import dev.yuanzix.revitalize.R

open class NavItem(val screen: Screen, val title: String, @DrawableRes val iconId: Int)

object NavRoutes {
    val Home = NavItem(
        screen = Screen.HomeScreen,
        title = NavPath.Home.toString(),
        iconId = R.drawable.home
    )
    val Attendance = NavItem(
        screen = Screen.AttendanceScreen,
        title = NavPath.Attendance.toString(),
        iconId = R.drawable.attendance
    )
    val Performance = NavItem(
        screen = Screen.PerformanceScreen,
        title = NavPath.Performance.toString(),
        iconId = R.drawable.performance
    )
    val Others =
        NavItem(
            screen = Screen.OthersScreen,
            title = NavPath.Others.toString(),
            iconId = R.drawable.more
        )
}