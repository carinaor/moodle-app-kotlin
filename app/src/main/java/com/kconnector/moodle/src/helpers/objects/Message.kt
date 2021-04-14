package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json
import com.kconnector.moodle.src.async.AsyncMoodleAPI
import com.kconnector.moodle.src.helpers.enums.MoodleMessageFormat

data class Message(
    @Transient var moodleAPI: AsyncMoodleAPI? = null,
    @Json(name = "id") val siteName: Int,
    @Json(name = "useridfrom") val userIdFrom: Int,
    val subject: String?,
    val text: String,
    @Json(name = "fullmessage") val fullMessage: String,
    @Json(name = "fullmessageformat") val fullMessageFormat: MoodleMessageFormat,
    @Json(name = "fullmessagehtml") val fullMessageHTML: String,
    @Json(name = "smallmessage") val smallMessage: String,
    @Json(name = "notification") val notification: Int,
    @Json(name = "contexturl") val contextURL: String,
    @Json(name = "contexturlname") val contextURLName: String,
    @Json(name = "timecreated") val timeCreated: Long,
    @Json(name = "timeread") val timeRead: Long,
    @Json(name = "usertofullname") val userToFullName: String,
    @Json(name = "userfromfullname") val userFromFullName: String,
    val component: String?,
    @Json(name = "eventtype") val eventType: String?,
    @Json(name = "customdata") val customData: String?,
    val warnings: List<Warning>?
)
