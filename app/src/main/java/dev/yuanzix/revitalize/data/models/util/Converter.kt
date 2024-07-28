package dev.yuanzix.revitalize.data.models.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.yuanzix.revitalize.data.models.AttendanceDetail
import dev.yuanzix.revitalize.data.models.Course
import dev.yuanzix.revitalize.data.models.Subject

object Converter {
    private val gson = Gson()

    // attendance item
    @TypeConverter
    fun fromAttendanceDetailMap(value: Map<String, AttendanceDetail>): String {
        val type = object : TypeToken<Map<String, AttendanceDetail>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toAttendanceDetailMap(value: String): Map<String, AttendanceDetail> {
        val type = object : TypeToken<Map<String, AttendanceDetail>>() {}.type
        return gson.fromJson(value, type)
    }

    // grade history
    @TypeConverter
    fun fromStringMap(value: Map<String, String>): String {
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringMap(value: String): Map<String, String> {
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromSubjectMap(value: Map<String, Subject>): String {
        val type = object : TypeToken<Map<String, Subject>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toSubjectMap(value: String): Map<String, Subject> {
        val type = object : TypeToken<Map<String, Subject>>() {}.type
        return gson.fromJson(value, type)
    }

    // timetable
    @TypeConverter
    fun fromCourseList(value: List<Course>): String {
        val type = object : TypeToken<List<Course>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCourseList(value: String): List<Course> {
        val type = object : TypeToken<List<Course>>() {}.type
        return gson.fromJson(value, type)
    }
}
