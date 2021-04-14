package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json

data class Warning(
    val item: String?,
    @Json(name = "itemid") val itemId: Int?,
    @Json(name = "warningcode") val warningCode: String,
    @Json(name = "message") val message: String
)
