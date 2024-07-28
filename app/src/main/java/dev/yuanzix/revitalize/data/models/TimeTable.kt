package dev.yuanzix.revitalize.data.models

data class TimeTable(
    val Tuesday: List<Course>,
    val Wednesday: List<Course>,
    val Thursday: List<Course>,
    val Friday: List<Course>,
    val Saturday: List<Course>,
)
