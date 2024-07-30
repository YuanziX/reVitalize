package dev.yuanzix.revitalize.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.TIMETABLE_TABLE

@Entity(tableName = TIMETABLE_TABLE)
data class TimetableDay(
    @PrimaryKey val day: String,
    val courses: List<Course>,
)

data class Course(
    val classId: String,
    val code: String,
    val courseName: String,
    val endTime: String,
    val location: String,
    val slot: String,
    val startTime: String,
)