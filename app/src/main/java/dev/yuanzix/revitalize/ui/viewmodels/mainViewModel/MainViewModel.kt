package dev.yuanzix.revitalize.ui.viewmodels.mainViewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yuanzix.revitalize.data.api.API
import dev.yuanzix.revitalize.data.database.AttendanceDao
import dev.yuanzix.revitalize.data.database.GradeHistoryDao
import dev.yuanzix.revitalize.data.database.ProfileDao
import dev.yuanzix.revitalize.data.database.SemesterDetailsDao
import dev.yuanzix.revitalize.data.database.TimeTableDao
import dev.yuanzix.revitalize.data.repository.DataStoreRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: API,
    private val dataStoreRepository: DataStoreRepository,
    private val attendanceDao: AttendanceDao,
    private val profileDao: ProfileDao,
    private val gradeHistoryDao: GradeHistoryDao,
    private val semesterDetailsDao: SemesterDetailsDao,
    private val timeTableDao: TimeTableDao
): ViewModel() {

}