package dev.yuanzix.revitalize.data.constants

object ApiConstants {
    private const val BASE_URL = PrivateConstants.BASE_URL

    // api endpoint constants
    private const val VERIFY_ENDPOINT = "/api/verify"
    private const val PROFILE_END_POINT = "/api/profile"
    private const val ATTENDANCE_ENDPOINT = "/api/attendance"
    private const val TIMETABLE_ENDPOINT = "/api/timetable"
    private const val SEM_IDS_ENDPOINT = "/api/semIDs"
    private const val MARKS_ENDPOINT = "/api/marks"
    private const val GRADES_ENDPOINT = "/api/grades"
    private const val EXAM_SCHEDULE_ENDPOINT = "/api/examSchedule"
    private const val ALL_DATA_ENDPOINT = "/api/all"

    // api url constants
    const val VERIFY_URL = "$BASE_URL$VERIFY_ENDPOINT"
    const val PROFILE_URL = "$BASE_URL$PROFILE_END_POINT"
    const val ATTENDANCE_URL = "$BASE_URL$ATTENDANCE_ENDPOINT"
    const val TIMETABLE_URL = "$BASE_URL$TIMETABLE_ENDPOINT"
    const val SEM_IDS_URL = "$BASE_URL$SEM_IDS_ENDPOINT"
    const val MARKS_URL = "$BASE_URL$MARKS_ENDPOINT"
    const val GRADES_URL = "$BASE_URL$GRADES_ENDPOINT"
    const val EXAM_SCHEDULE_URL = "$BASE_URL$EXAM_SCHEDULE_ENDPOINT"
    const val ALL_DATA_URL = "$BASE_URL$ALL_DATA_ENDPOINT"

    // hostel sophos client urls
    const val HOSTEL_WIFI_LOGIN_URL = "https://hfw.vitap.ac.in:8090/login.xml"
    const val HOSTEL_WIFI_LOGOUT_URL = "https://hfw.vitap.ac.in:8090/logout.xml"
}