package dev.yuanzix.revitalize.navigation

import dev.yuanzix.revitalize.R

open class NavItem(val screen: Screen, val title: String, val icon: Int)

object NavRoutes {
    val Home = NavItem(Screen.HomeScreen, NavPath.Home.toString(), R.drawable.outline_home_app_logo)
    val Attendance = NavItem(Screen.AttendanceScreen, NavPath.Attendance.toString(), R.drawable.rounded_calendar_month)
    val Performance = NavItem(Screen.PerformanceScreen, NavPath.Performance.toString(), R.drawable.rounded_numbers)
    val Others = NavItem(Screen.OthersScreen, NavPath.Others.toString(), R.drawable.rounded_more_horiz)
}