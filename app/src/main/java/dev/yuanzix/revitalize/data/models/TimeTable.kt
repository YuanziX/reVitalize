package dev.yuanzix.revitalize.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.TIMETABLE_TABLE

@Entity(tableName = TIMETABLE_TABLE)
data class TimeTable(
    @PrimaryKey @SerializedName("Tuesday") val tuesday: List<Course>,
    @SerializedName("Wednesday") val wednesday: List<Course>,
    @SerializedName("Thursday") val thursday: List<Course>,
    @SerializedName("Friday") val friday: List<Course>,
    @SerializedName("Saturday") val saturday: List<Course>,
)
