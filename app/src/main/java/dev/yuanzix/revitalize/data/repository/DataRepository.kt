package dev.yuanzix.revitalize.data.repository

import dev.yuanzix.revitalize.data.api.API
import dev.yuanzix.revitalize.data.database.AttendanceDao
import dev.yuanzix.revitalize.data.database.GradeHistoryDao
import dev.yuanzix.revitalize.data.database.ProfileDao
import dev.yuanzix.revitalize.data.database.SemesterDetailsDao
import dev.yuanzix.revitalize.data.database.TimeTableDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository(
    private val timeTableDao: TimeTableDao,
    private val attendanceDao: AttendanceDao,
    private val gradeHistoryDao: GradeHistoryDao,
    private val profileDao: ProfileDao,
    private val semesterDetailsDao: SemesterDetailsDao
) {

    /**
     * Fetch and save TimeTable data.
     */
    suspend fun refreshTimeTableData(username: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val data = API.getTimeTableData(username, password)
                timeTableDao.insertTimetable(data)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    /**
     * Fetch and save Attendance data.
     */
    suspend fun refreshAttendanceData(username: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val data = API.getAttendanceData(username, password)
                attendanceDao.insertAll(data)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    /**
     * Fetch and save GradeHistory data.
     */
    suspend fun refreshGradeHistoryData(username: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val data = API.getGradesData(username, password)
                gradeHistoryDao.insertGradeHistory(data)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    /**
     * Fetch and save Profile data.
     */
    suspend fun refreshProfileData(username: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val data = API.getProfileData(username, password)
                profileDao.insertProfile(data)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    /**
     * Fetch and save SemesterDetails data.
     */
    suspend fun refreshSemesterDetailsData(username: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val data = API.getSemIdsData(username, password)
                semesterDetailsDao.insertSemesterDetails(data)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
