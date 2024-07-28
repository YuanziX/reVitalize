package dev.yuanzix.revitalize.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.SEMESTER_ID_TABLE

@Entity(tableName = SEMESTER_ID_TABLE)
data class SemesterDetails(
    @PrimaryKey @SerializedName("semIDs") val semester: Map<String, String>
)