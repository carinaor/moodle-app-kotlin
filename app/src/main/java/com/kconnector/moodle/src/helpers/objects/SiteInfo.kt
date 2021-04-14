package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json

data class SiteInfo(
    @Json(name = "sitename") val siteName: String,
    val username: String,
    @Json(name = "firstname") val firstName: String,
    @Json(name = "lastname") val lastName: String,
    @Json(name = "fullname") val fullName: String,
    val lang: String,
    @Json(name = "userid") val userId: Int,
    @Json(name = "siteurl") val siteURL: String,
    @Json(name = "userpictureurl") val userPictureURL: String,
    @Json(name = "downloadfiles") val downloadFiles: Boolean?,
    @Json(name = "uploadfiles") val uploadFiles: Boolean?,
    val release: String?,
    val version: String?,
    @Json(name = "mobilecssurl") val mobileCSSURL: String?,
    @Json(name = "usercanmanageownfiles") val userCanMangeOwnFiles: Boolean?,
    @Json(name = "userquota") val userQuota: Int?,
    @Json(name = "usermaxuploadfilesize") val userMaxUploadFileSize: Int?,
    @Json(name = "userhomepage") val userHomePage: Int?,
    @Json(name = "userprivateaccesskey") val userPrivateAccessKey: String?,
    @Json(name = "siteid") val siteId: Int?,
    @Json(name = "sitecalendartype") val siteCalenderType: String?,
    @Json(name = "usercalendartype") val userCalenderType: String?,
    @Json(name = "userissiteadmin") val userIsSiteAdmin: Boolean?,
    @Json(name = "theme") val theme: String?
)
