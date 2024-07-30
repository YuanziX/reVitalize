package dev.yuanzix.revitalize.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.yuanzix.revitalize.data.models.AttendanceItem
import dev.yuanzix.revitalize.data.models.GradeHistory
import dev.yuanzix.revitalize.data.models.ProfileItem
import dev.yuanzix.revitalize.data.models.Semester
import dev.yuanzix.revitalize.data.models.TimetableDay
import dev.yuanzix.revitalize.data.models.util.Converter

@Database(
    entities = [AttendanceItem::class, GradeHistory::class, ProfileItem::class, Semester::class, TimetableDay::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class ReVitalizeDatabase : RoomDatabase() {
    abstract fun getAttendanceDao(): AttendanceDao
    abstract fun getGradeHistoryDao(): GradeHistoryDao
    abstract fun getProfileDao(): ProfileDao
    abstract fun getSemesterDetailsDao(): SemesterDao
    abstract fun getTimeTableDao(): TimeTableDao
}