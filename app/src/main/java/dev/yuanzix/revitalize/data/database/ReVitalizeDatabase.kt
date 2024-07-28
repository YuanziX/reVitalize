package dev.yuanzix.revitalize.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.yuanzix.revitalize.data.models.AttendanceItem
import dev.yuanzix.revitalize.data.models.GradeHistory
import dev.yuanzix.revitalize.data.models.Profile
import dev.yuanzix.revitalize.data.models.SemesterDetails
import dev.yuanzix.revitalize.data.models.TimeTable
import dev.yuanzix.revitalize.data.models.util.Converter

@Database(
    entities = [AttendanceItem::class, GradeHistory::class, Profile::class, SemesterDetails::class, TimeTable::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class ReVitalizeDatabase : RoomDatabase() {
    abstract fun getAttendanceDao(): AttendanceDao
    abstract fun getGradeHistoryDao(): GradeHistoryDao
    abstract fun getProfileDao(): ProfileDao
    abstract fun getSemesterDetailsDao(): SemesterDetailsDao
    abstract fun getTimeTableDao(): TimeTableDao
}