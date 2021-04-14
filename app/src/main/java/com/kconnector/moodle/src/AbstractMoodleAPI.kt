package com.kconnector.moodle.src

import com.kconnector.moodle.src.helpers.exceptions.AuthenticationException
import com.kconnector.moodle.src.helpers.exceptions.InvalidDataException
import com.kconnector.moodle.src.http.HTTP
import com.kconnector.moodle.src.helpers.objects.SiteInfo
import com.kconnector.moodle.src.requests.Request
import com.kconnector.moodle.src.helpers.serializer.JSONSerializer

abstract class AbstractMoodleAPI(url: String) {
    private val url: String = url.replace("\\/+", "/").removeSuffix("/")
    protected val endpointURL: String = url + com.kconnector.moodle.src.MoodleConstants.DEFAULT_ENDPOINT
    private lateinit var accessToken: String
    protected lateinit var siteInfo: SiteInfo

    constructor(url: String, accessToken: String) : this(url) {
        this.accessToken = accessToken
    }

    @Throws(AuthenticationException::class)
    fun login(username: String, password: String): String {
        val response = HTTP.getJson(url + com.kconnector.moodle.src.MoodleConstants.LOGIN_ENDPOINT, mapOf("username" to username, "password" to password, "service" to "moodle_mobile_app")) as Map<String, Any>
        if (response.containsKey("errorcode")) {
            throw AuthenticationException(response["error"]!! as String)
        }
        this.accessToken = response["token"]!! as String
        return accessToken
    }

    protected fun prepareRequest(request: Request): Map<String, Any> {
        val data = mutableMapOf<String, Any>()
        request.write(data)
        if (data.containsKey(com.kconnector.moodle.src.MoodleConstants.TOKEN_FIELD_NAME)) {
            throw InvalidDataException("${com.kconnector.moodle.src.MoodleConstants.TOKEN_FIELD_NAME} is a registered keyword!")
        }
        if (data.containsKey(com.kconnector.moodle.src.MoodleConstants.FUNCTION_FIELD_NAME)) {
            throw InvalidDataException("${com.kconnector.moodle.src.MoodleConstants.FUNCTION_FIELD_NAME} is a registered keyword!")
        }
        if (data.containsKey(com.kconnector.moodle.src.MoodleConstants.REST_FORMAT_FIELD_NAME)) {
            throw InvalidDataException("${com.kconnector.moodle.src.MoodleConstants.REST_FORMAT_FIELD_NAME} is a registered keyword!")
        }
        data[com.kconnector.moodle.src.MoodleConstants.TOKEN_FIELD_NAME] = accessToken
        data[com.kconnector.moodle.src.MoodleConstants.FUNCTION_FIELD_NAME] = com.kconnector.moodle.src.RequestMapping.getFunctionByPacket(request::class)!!
        data[com.kconnector.moodle.src.MoodleConstants.REST_FORMAT_FIELD_NAME] = com.kconnector.moodle.src.MoodleConstants.REST_FORMAT_FIELD_VALUE
        return com.kconnector.moodle.src.AbstractMoodleAPI.Companion.formatDataToMoodle(data.toMap())
    }

    abstract fun initAPI()

    abstract fun shutdown()

    companion object {
        private fun formatMapToMoodle(keyPrefix: String, output: MutableMap<String, Any>, data: Map<String, Any>) {
            for ((key, value) in data) {
                val currentPrefix = if (keyPrefix.isBlank()) {
                    key
                } else {
                    "$keyPrefix[$key]"
                }
                com.kconnector.moodle.src.AbstractMoodleAPI.Companion.formatDataToMoodle(currentPrefix, output, value)
            }

        }

        private fun formatDataToMoodle(keyPrefix: String, output: MutableMap<String, Any>, data: Any) {
            when (data) {
                is Map<*, *> -> {
                    com.kconnector.moodle.src.AbstractMoodleAPI.Companion.formatMapToMoodle(keyPrefix, output, data as Map<String, Any>)
                }
                is List<*> -> {
                    com.kconnector.moodle.src.AbstractMoodleAPI.Companion.formatListToMoodle(keyPrefix, output, data as List<Any>)
                }
                else -> {
                    if (data::class.isData) {
                        com.kconnector.moodle.src.AbstractMoodleAPI.Companion.formatMapToMoodle(keyPrefix, output, JSONSerializer.objectToMap(data))
                    } else {
                        output[keyPrefix] = data
                    }
                }
            }
        }

        private fun formatListToMoodle(keyPrefix: String, output: MutableMap<String, Any>, data: List<Any>) {
            for ((i, entry) in data.withIndex()) {
                com.kconnector.moodle.src.AbstractMoodleAPI.Companion.formatDataToMoodle("$keyPrefix[${i}]", output, entry)
            }
        }

        private fun formatDataToMoodle(data: Map<String, Any>): Map<String, Any> {
            val output: MutableMap<String, Any> = mutableMapOf()
            com.kconnector.moodle.src.AbstractMoodleAPI.Companion.formatMapToMoodle("", output, data)
            return output
        }
    }

}
