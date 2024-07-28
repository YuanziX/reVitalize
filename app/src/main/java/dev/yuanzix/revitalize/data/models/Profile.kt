package dev.yuanzix.revitalize.data.models

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("Application Number") val applicationNumber: String,
    @SerializedName("Mentor Cabin") val mentorCabin: String,
    @SerializedName("Mentor Email") val mentorEmail: String,
    @SerializedName("Mentor Mobile Number") val mentorMobileNumber: String,
    @SerializedName("Mentor Name") val mentorName: String,
    @SerializedName("Mentor intercom") val mentorIntercom: String,
    @SerializedName("Student Name") val studentName: String,
    @SerializedName("VIT Registration Number") val vitRegistrationNumber: String,
    @SerializedName("image") val image: String
)
