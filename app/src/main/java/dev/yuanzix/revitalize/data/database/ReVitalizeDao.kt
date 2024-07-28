package dev.yuanzix.revitalize.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.ATTENDANCE_TABLE
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.GRADE_HISTORY_TABLE
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.PROFILE_TABLE
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.SEMESTER_ID_TABLE
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.TIMETABLE_TABLE
import dev.yuanzix.revitalize.data.models.AttendanceItem
import dev.yuanzix.revitalize.data.models.GradeHistory
import dev.yuanzix.revitalize.data.models.Profile
import dev.yuanzix.revitalize.data.models.SemesterDetails
import dev.yuanzix.revitalize.data.models.TimeTable
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(attendanceItems: List<AttendanceItem>)

    @Query("SELECT * FROM $ATTENDANCE_TABLE")
    fun getAll(): Flow<List<AttendanceItem>>
}

@Dao
interface TimeTableDao {
    @Query("DELETE FROM $TIMETABLE_TABLE")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timeTable: TimeTable)

    @Transaction
    suspend fun insertTimetable(timeTable: TimeTable) {
        delete()
        insert(timeTable)
    }

    @Query("SELECT * FROM $TIMETABLE_TABLE LIMIT 1")
    fun get(): Flow<TimeTable>
}

@Dao
interface SemesterDetailsDao {
    @Query("DELETE FROM $SEMESTER_ID_TABLE")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(semesterDetails: SemesterDetails)

    @Transaction
    suspend fun insertSemesterDetails(semesterDetails: SemesterDetails) {
        delete()
        insert(semesterDetails)
    }

    @Query("SELECT * FROM $SEMESTER_ID_TABLE LIMIT 1")
    fun get(): Flow<SemesterDetails>
}

@Dao
interface ProfileDao {
    @Query("DELETE FROM $PROFILE_TABLE")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: Profile)

    @Transaction
    suspend fun insertProfile(profile: Profile) {
        delete()
        insert(profile)
    }

    @Query("SELECT * FROM $PROFILE_TABLE LIMIT 1")
    fun get(): Flow<Profile>
}

@Dao
interface GradeHistoryDao {
    @Query("DELETE FROM $GRADE_HISTORY_TABLE")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gradeHistory: GradeHistory)

    @Transaction
    suspend fun insertGradeHistory(gradeHistory: GradeHistory) {
        delete()
        insert(gradeHistory)
    }

    @Query("SELECT * FROM $GRADE_HISTORY_TABLE LIMIT 1")
    fun get(): Flow<GradeHistory>
}