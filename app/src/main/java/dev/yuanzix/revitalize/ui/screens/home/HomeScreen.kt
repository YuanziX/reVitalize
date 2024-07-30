package dev.yuanzix.revitalize.ui.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yuanzix.revitalize.ui.screens.loading.LoadingScreen
import dev.yuanzix.revitalize.ui.viewmodels.mainViewModel.MainViewModel
import dev.yuanzix.revitalize.util.RequestState

@Composable
fun HomeScreen(
) {
    val mainViewModel: MainViewModel = hiltViewModel()

    if (mainViewModel.todayTimetable.value is RequestState.Success) {
        val timetable = (mainViewModel.todayTimetable.value as RequestState.Success).data

        if (timetable.courses.isEmpty()) {
            Text("No courses today")
        } else {
            LazyColumn {
                items(timetable.courses) { course ->
                    CourseCard(course)
                }
            }
        }
    } else {
        LoadingScreen()
    }
}