package dev.yuanzix.revitalize.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.ATTENDANCE_TABLE

@Entity(tableName = ATTENDANCE_TABLE)
data class AttendanceItem(
    @PrimaryKey val classID: String,
    val attendanceDetail: Map<String, AttendanceDetail>,
    val attendancePercentage: String,
    val attendedClasses: String,
    val courseType: String,
    val name: String,
    val slot: String,
    val totalClasses: String,
)