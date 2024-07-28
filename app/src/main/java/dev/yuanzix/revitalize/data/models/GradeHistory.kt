package dev.yuanzix.revitalize.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.GRADE_HISTORY_TABLE

@Entity(tableName = GRADE_HISTORY_TABLE)
data class GradeHistory(
    @PrimaryKey val cgpa: String,
    val creditsEarned: String,
    val numOfEachGrade: Map<String, String>,
    val subjects: Map<String, Subject>
)
