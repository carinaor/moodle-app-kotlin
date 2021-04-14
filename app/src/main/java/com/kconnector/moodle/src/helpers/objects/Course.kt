package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json
import com.kconnector.moodle.src.async.AsyncMoodleAPI
import com.kconnector.moodle.src.helpers.enums.MoodleMessageFormat

data class Course(
    @Transient var moodleAPI: AsyncMoodleAPI? = null,
    val id: Int,
    @Json(name = "fullname") val fullName: String,
    @Json(name = "shortname") val shortName: String,
    @Json(name = "idnumber") val isNumber: String,
    val summary: String,
    @Json(name = "summaryformat") val summaryFormat: MoodleMessageFormat,
    @Json(name = "startdate") val startDate: Long,
    @Json(name = "enddate") val endDate: Long,
    val visible: Boolean,
    @Json(name = "fullnamedisplay") val fullNameDisplay: String,
    @Json(name = "courseimage") val courseImage: String,
    @Json(name = "viewurl") val viewURL: String,
    val progress: Float?,
    @Json(name = "hasprogress") val hasProgress: Boolean,
    @Json(name = "isfavourite") val isFavourite: Boolean,
    val hidden: Boolean,
    @Json(name = "timeaccess") val timeAccess: Long?,
    @Json(name = "showshortname") val showShortName: Boolean,
    @Json(name = "coursecategory") val courseCategory: String
)
