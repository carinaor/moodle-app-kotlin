package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json
import com.kconnector.moodle.src.helpers.enums.MoodleMessageFormat

data class ConversationMessageToSend(
    val text: String,
    @Json(name = "textformat") val messageFormat: MoodleMessageFormat = MoodleMessageFormat.HTML
)
