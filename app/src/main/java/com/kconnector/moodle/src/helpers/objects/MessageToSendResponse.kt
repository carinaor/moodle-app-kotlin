package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json

data class MessageToSendResponse(
    @Json(name = "msgid") val messageId: Int,
    @Json(name = "clientmsgid") val clientMessageId: String,
    @Json(name = "errormessage") val errorMessage: String,
    val text: String,
    @Json(name = "timecreated") val timeCreated: Long,
    @Json(name = "conversationid") val conversationId: Int,
    @Json(name = "candeletemessagesforallusers") val canDeleteMessagesForAllUsers: Boolean
)
