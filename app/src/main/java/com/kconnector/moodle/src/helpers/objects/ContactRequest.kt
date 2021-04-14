package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json

data class ContactRequest(
    val id: Int,
    @Json(name = "userid") val userId: Int,
    @Json(name = "requesteduserid") val requestedUserId: Int,
    @Json(name = "timecreated") val timeCreated: Long
)
