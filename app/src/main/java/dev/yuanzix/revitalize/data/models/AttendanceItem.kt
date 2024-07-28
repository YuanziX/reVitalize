package dev.yuanzix.revitalize.data.models

data class AttendanceItem(
    val attendanceDetail: Map<String, AttendanceDetail>,
    val attendancePercentage: String,
    val attendedClasses: String,
    val classID: String,
    val courseType: String,
    val name: String,
    val slot: String,
    val totalClasses: String
)