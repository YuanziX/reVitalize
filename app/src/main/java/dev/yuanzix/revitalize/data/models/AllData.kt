package dev.yuanzix.revitalize.data.models

data class AllData(
    val attendance: Attendance,
    val grades: GradeHistory,
    val timetable: TimeTable,
    val profile: Profile,
    val semIDs: Map<String, String>
)
