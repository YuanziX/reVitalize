package dev.yuanzix.revitalize.data.models

data class GradeHistory(
    val cgpa: String,
    val creditsEarned: String,
    val numOfEachGrade: Map<String, String>,
    val subjects: Map<String, Subject>
)
