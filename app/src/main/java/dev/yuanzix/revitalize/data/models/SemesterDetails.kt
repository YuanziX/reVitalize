package dev.yuanzix.revitalize.data.models

import com.google.gson.annotations.SerializedName

data class SemesterDetails(
    @SerializedName("semIDs") val semester: Map<String, String>
)