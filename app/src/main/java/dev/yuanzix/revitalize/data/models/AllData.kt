package dev.yuanzix.revitalize.data.models

data class AllData(
    val attendance: Attendance,
    val grades: GradeHistory,
    val timetable: Map<String, List<Course>>,
    val profile: Map<String, String>,
    val semIDs: Map<String, String>,
) {
    fun timetableDays(): List<TimetableDay> = timetable.map { (day, courses) ->
        TimetableDay(day, courses)
    }

    fun profileItems(): List<ProfileItem> =
        profile.map { (title, value) -> ProfileItem(title, value) }

    fun semesters(): List<Semester> = semIDs.map { (id, name) -> Semester(id, name) }
}
