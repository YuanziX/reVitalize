package dev.yuanzix.revitalize.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.PROFILE_TABLE

@Entity(tableName = PROFILE_TABLE)
data class Profile(
    @PrimaryKey @SerializedName("Application Number") val applicationNumber: String,
    @SerializedName("Mentor Cabin") val mentorCabin: String,
    @SerializedName("Mentor Email") val mentorEmail: String,
    @SerializedName("Mentor Mobile Number") val mentorMobileNumber: String,
    @SerializedName("Mentor Name") val mentorName: String,
    @SerializedName("Mentor intercom") val mentorIntercom: String,
    @SerializedName("Student Name") val studentName: String,
    @SerializedName("VIT Registration Number") val vitRegistrationNumber: String,
    @SerializedName("image") val image: String
)
