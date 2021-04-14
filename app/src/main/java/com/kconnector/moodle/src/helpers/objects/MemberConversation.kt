package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json
import com.kconnector.moodle.src.helpers.enums.MoodleMemberConversationType

data class MemberConversation(
    val id: Int,
    val type: MoodleMemberConversationType,
    val name: String,
    @Json(name = "timecreated") val timeCreated: Long
)
