package dev.yuanzix.revitalize.data.repository

import android.annotation.SuppressLint
import dev.yuanzix.revitalize.data.api.Api
import dev.yuanzix.revitalize.data.database.AttendanceDao
import dev.yuanzix.revitalize.data.database.GradeHistoryDao
import dev.yuanzix.revitalize.data.database.ProfileDao
import dev.yuanzix.revitalize.data.database.SemesterDao
import dev.yuanzix.revitalize.data.database.TimeTableDao
import dev.yuanzix.revitalize.data.models.AllData
import dev.yuanzix.revitalize.data.models.AttendanceItem
import dev.yuanzix.revitalize.data.models.GradeHistory
import dev.yuanzix.revitalize.data.models.ProfileItem
import dev.yuanzix.revitalize.data.models.Semester
import dev.yuanzix.revitalize.data.models.TimetableDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val timeTableDao: TimeTableDao,
    private val attendanceDao: AttendanceDao,
    private val gradeHistoryDao: GradeHistoryDao,
    private val profileDao: ProfileDao,
    private val semesterDao: SemesterDao,
) {

    /**
     * Fetch and save TimeTable data if the database is empty or pullNewData is true.
     */
    @SuppressLint("SimpleDateFormat")
    suspend fun getTodayTimeTableData(
        userCredentials: UserCredentials,
        pullNewData: Boolean = false,
    ): TimetableDay {
        return withContext(Dispatchers.IO) {
            try {
                if (pullNewData || timeTableDao.isEmpty()) {
                    val apiResponse =
                        Api.getTimeTableData(userCredentials.username, userCredentials.password)
                    timeTableDao.insertAll(apiResponse)
                }

                val day = SimpleDateFormat("EEEE").format(Date())
                @Suppress("USELESS_ELVIS") // Not useless
                timeTableDao.getDay(day) ?: TimetableDay(day = day, courses = emptyList())
            } catch (e: Exception) {
                throw e
            }
        }
    }

    /**
     * Fetch and save Attendance data if the database is empty or pullNewData is true.
     */
    suspend fun getAttendanceData(
        userCredentials: UserCredentials,
        pullNewData: Boolean = false,
    ): Flow<List<AttendanceItem>> {
        return withContext(Dispatchers.IO) {
            try {
                if (pullNewData || attendanceDao.isEmpty()) {
                    val apiResponse =
                        Api.getAttendanceData(userCredentials.username, userCredentials.password)
                    if (apiResponse.isNotEmpty()) {
                        attendanceDao.insertAll(apiResponse)
                    }
                }
                attendanceDao.getAll()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    /**
     * Fetch and save GradeHistory data if the database is empty or pullNewData is true.
     */
    suspend fun getGradeHistoryData(
        userCredentials: UserCredentials,
        pullNewData: Boolean = false,
    ): Flow<GradeHistory> {
        return withContext(Dispatchers.IO) {
            try {
                if (pullNewData || gradeHistoryDao.isEmpty()) {
                    val apiResponse =
                        Api.getGradesData(userCredentials.username, userCredentials.password)
                    if (apiResponse.numOfEachGrade.isNotEmpty()) {
                        gradeHistoryDao.insertGradeHistory(apiResponse)
                    }
                }
                gradeHistoryDao.get()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    /**
     * Fetch and save Profile data if the database is empty or pullNewData is true.
     */
    suspend fun getProfileData(
        userCredentials: UserCredentials,
        pullNewData: Boolean = false,
    ): Flow<List<ProfileItem>> {
        return withContext(Dispatchers.IO) {
            try {
                if (pullNewData || profileDao.isEmpty()) {
                    val apiResponse =
                        Api.getProfileData(userCredentials.username, userCredentials.password)
                    if (apiResponse.isNotEmpty()) {
                        profileDao.insert(apiResponse)
                    }
                }
                profileDao.get()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    /**
     * Fetch and save SemesterDetails data if the database is empty or pullNewData is true.
     */
    suspend fun getSemesterDetailsData(
        userCredentials: UserCredentials,
        pullNewData: Boolean = false,
    ): Flow<List<Semester>> {
        return withContext(Dispatchers.IO) {
            try {
                if (pullNewData || semesterDao.isEmpty()) {
                    val apiResponse =
                        Api.getSemIdsData(userCredentials.username, userCredentials.password)
                    if (apiResponse.isNotEmpty()) {
                        semesterDao.insert(apiResponse)
                    }
                }
                semesterDao.get()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun refreshAllData(
        userCredentials: UserCredentials,
    ) {
        withContext(Dispatchers.IO) {
            try {
                val allData: AllData =
                    Api.getAllData(userCredentials.username, userCredentials.password)
                if (allData.timetable.isNotEmpty()) {
                    timeTableDao.insertAll(allData.timetableDays())
                    semesterDao.insert(allData.semesters())
                    attendanceDao.insertAll(allData.attendance)
                    gradeHistoryDao.insertGradeHistory(allData.grades)
                    profileDao.insert(allData.profileItems())
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }
}
