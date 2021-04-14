package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json
import com.kconnector.moodle.src.helpers.enums.MoodleMessageFormat

data class MessageToSend(
    @Json(name = "touserid") val toUserId: Int,
    val text: String,
    @Json(name = "textformat") val messageFormat: MoodleMessageFormat = MoodleMessageFormat.HTML,
    @Json(name = "clientmsgid") val clientMessageId: Int?
)
