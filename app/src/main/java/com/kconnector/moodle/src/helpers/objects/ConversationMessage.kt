package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json

data class ConversationMessage(
    val id: Int,
    @Json(name = "useridfrom") val userIdFrom: Int,
    val text: String,
    @Json(name = "timecreated") val timeCreated: Long
)
