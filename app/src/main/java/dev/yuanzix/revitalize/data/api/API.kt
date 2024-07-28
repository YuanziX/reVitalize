package dev.yuanzix.revitalize.data.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.yuanzix.revitalize.data.constants.ApiConstants
import dev.yuanzix.revitalize.data.models.AllData
import dev.yuanzix.revitalize.data.models.Attendance
import dev.yuanzix.revitalize.data.models.Course
import dev.yuanzix.revitalize.data.models.CredentialStatus
import dev.yuanzix.revitalize.data.models.GradeHistory
import dev.yuanzix.revitalize.data.models.ProfileItem
import dev.yuanzix.revitalize.data.models.Semester
import dev.yuanzix.revitalize.data.models.TimetableDay
import dev.yuanzix.revitalize.network.NetworkUtils

object API {
    private val gson = Gson()
    private fun getCredentialsPayload(username: String, password: String) =
        mapOf("username" to username, "password" to password)

    fun getTimeTableData(username: String, password: String): List<TimetableDay> {
        try {
            return gson.fromJson<Map<String, List<Course>>?>(
                NetworkUtils.post(
                    ApiConstants.TIMETABLE_URL, getCredentialsPayload(username, password)
                ), object : TypeToken<Map<String, List<Course>>>() {}.type
            ).map { (day, courses) ->
                TimetableDay(day, courses)
            }
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

    fun getProfileData(username: String, password: String): List<ProfileItem> {
        try {
            return gson.fromJson<Map<String, String>?>(
                NetworkUtils.post(
                    ApiConstants.PROFILE_URL, getCredentialsPayload(username, password)
                ), object : TypeToken<Map<String, String>>() {}.type
            ).map { (title, value) ->
                ProfileItem(title, value)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun getSemIdsData(username: String, password: String): List<Semester> {
        try {
            return gson.fromJson<Map<String, String>?>(
                NetworkUtils.post(
                    ApiConstants.SEM_IDS_URL, getCredentialsPayload(username, password)
                ), object : TypeToken<Map<String, String>>() {}.type
            ).map { (title, value) ->
                Semester(title, value)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun getAllData(username: String, password: String): AllData {
        try {
            val request = NetworkUtils.post(
                ApiConstants.ALL_DATA_URL, getCredentialsPayload(username, password)
            )
            return gson.fromJson(
                request, AllData::class.java
            )
        } catch (e: Exception) {
            throw e
        }
    }

    fun verifyCredentials(username: String, password: String): CredentialStatus {
        try {
            val status = NetworkUtils.postStatusCode(
                ApiConstants.VERIFY_URL, getCredentialsPayload(username, password)
            )
            return if (status == 200) {
                CredentialStatus.Correct
            } else {
                CredentialStatus.Incorrect
            }
        } catch (e: Exception) {
            return CredentialStatus.SomethingWentWrong
        }
    }
}
