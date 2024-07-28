package dev.yuanzix.revitalize.data.api

import com.google.gson.Gson
import dev.yuanzix.revitalize.data.constants.ApiConstants
import dev.yuanzix.revitalize.data.models.AllData
import dev.yuanzix.revitalize.data.models.Attendance
import dev.yuanzix.revitalize.data.models.GradeHistory
import dev.yuanzix.revitalize.data.models.Profile
import dev.yuanzix.revitalize.data.models.SemesterDetails
import dev.yuanzix.revitalize.data.models.TimeTable
import dev.yuanzix.revitalize.network.NetworkUtils

object API {
    private val gson = Gson()
    private fun getCredentialsPayload(username: String, password: String) =
        mapOf("username" to username, "password" to password)

    fun getTimeTableData(username: String, password: String): TimeTable {
        try {
            return gson.fromJson(
                NetworkUtils.post(
                    ApiConstants.TIMETABLE_URL, getCredentialsPayload(username, password)
                ), TimeTable::class.java
            )
        } catch (e: Exception) {
            throw e
        }
    }

    fun getAttendanceData(username: String, password: String): Attendance {
        try {
            return gson.fromJson(
                NetworkUtils.post(
                    ApiConstants.ATTENDANCE_URL, getCredentialsPayload(username, password)
                ), Attendance::class.java
            )
        } catch (e: Exception) {
            throw e
        }
    }

    fun getGradesData(username: String, password: String): GradeHistory {
        try {
            return gson.fromJson(
                NetworkUtils.post(
                    ApiConstants.GRADES_URL, getCredentialsPayload(username, password)
                ), GradeHistory::class.java
            )
        } catch (e: Exception) {
            throw e
        }
    }

    fun getProfileData(username: String, password: String): Profile {
        try {
            return gson.fromJson(
                NetworkUtils.post(
                    ApiConstants.PROFILE_URL, getCredentialsPayload(username, password)
                ), Profile::class.java
            )
        } catch (e: Exception) {
            throw e
        }
    }

    fun getSemIdsData(username: String, password: String): SemesterDetails {
        try {
            return gson.fromJson(
                "{\"semIDs\": ${
                    NetworkUtils.post(
                        ApiConstants.SEM_IDS_URL, getCredentialsPayload(username, password)
                    )
                }}", SemesterDetails::class.java
            )
        } catch (e: Exception) {
            throw e
        }
    }

    fun getAllData(username: String, password: String): AllData {
        try {
            return gson.fromJson(
                NetworkUtils.post(
                    ApiConstants.ALL_DATA_URL, getCredentialsPayload(username, password)
                ), AllData::class.java
            )
        } catch (e: Exception) {
            throw e
        }
    }
}