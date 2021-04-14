package com.kconnector.moodle.src

object MoodleConstants {

    //const val DEFAULT_ENDPOINT = "http://localhost:8080/moodle_api/rest/server.php"

    const val DEFAULT_ENDPOINT = "/webservice/rest/server.php"
    const val LOGIN_ENDPOINT = "/login/token.php"
    const val TOKEN_FIELD_NAME = "wstoken"
    const val FUNCTION_FIELD_NAME = "wsfunction"
    const val REST_FORMAT_FIELD_NAME = "moodlewsrestformat"
    const val REST_FORMAT_FIELD_VALUE = "json"
}