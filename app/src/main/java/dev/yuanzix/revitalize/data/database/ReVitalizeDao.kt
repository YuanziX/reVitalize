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
import dev.yuanzix.revitalize.data.models.ProfileItem
import dev.yuanzix.revitalize.data.models.Semester
import dev.yuanzix.revitalize.data.models.TimetableDay
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(attendanceItems: List<AttendanceItem>)

    @Query("SELECT * FROM $ATTENDANCE_TABLE")
    fun getAll(): Flow<List<AttendanceItem>>

    @Query("SELECT (SELECT COUNT(*) FROM $ATTENDANCE_TABLE) == 0")
    suspend fun isEmpty(): Boolean
}

@Dao
interface TimeTableDao {
    @Query("DELETE FROM $TIMETABLE_TABLE")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(timetableDay: TimetableDay)

    @Query("SELECT * FROM $TIMETABLE_TABLE")
    suspend fun getAll(): TimetableDay

    @Query("SELECT * FROM $TIMETABLE_TABLE WHERE day = :day")
    suspend fun getDay(day: String): TimetableDay

    @Query("SELECT (SELECT COUNT(*) FROM $TIMETABLE_TABLE) == 0")
    suspend fun isEmpty(): Boolean
}

@Dao
interface SemesterDetailsDao {
    @Query("DELETE FROM $SEMESTER_ID_TABLE")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(semesters: List<Semester>)

    @Query("SELECT * FROM $SEMESTER_ID_TABLE")
    fun get(): Flow<List<Semester>>

    @Query("SELECT (SELECT COUNT(*) FROM $SEMESTER_ID_TABLE) == 0")
    suspend fun isEmpty(): Boolean
}

@Dao
interface ProfileDao {
    @Query("DELETE FROM $PROFILE_TABLE")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profileItems: List<ProfileItem>)

    @Query("SELECT * FROM $PROFILE_TABLE")
    fun get(): Flow<List<ProfileItem>>

    @Query("SELECT (SELECT COUNT(*) FROM $PROFILE_TABLE) == 0")
    suspend fun isEmpty(): Boolean
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

    @Query("SELECT (SELECT COUNT(*) FROM $GRADE_HISTORY_TABLE) == 0")
    suspend fun isEmpty(): Boolean
}
