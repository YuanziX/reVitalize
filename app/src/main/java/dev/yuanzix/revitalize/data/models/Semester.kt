package dev.yuanzix.revitalize.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.SEMESTER_ID_TABLE

@Entity(tableName = SEMESTER_ID_TABLE)
data class Semester(
    @PrimaryKey val semesterID: String,
    val semester: String,
)