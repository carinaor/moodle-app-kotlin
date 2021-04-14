package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json

data class ConversationMember(
    val id: Int,
    val fullname: String,
    @Json(name = "profileurl") val profileURL: String,
    @Json(name = "profileimageurl") val profileImageURL: String,
    @Json(name = "profileimageurlsmall") val profileImageURLSmall: String,
    @Json(name = "isonline") val isOnline: Boolean?, // ToDo: expected non null
    @Json(name = "showonlinestatus") val showOnlineStatus: Boolean,
    @Json(name = "isblocked") val isBlocked: Boolean,
    @Json(name = "iscontact") val isContact: Boolean,
    @Json(name = "isdeleted") val isDeleted: Boolean,
    @Json(name = "canmessageevenifblocked") val canMessageEvenIfBlocked: Boolean?, // ToDo: expected non null
    @Json(name = "canmessage") val canMessage: Boolean?, // ToDo: expected non null
    @Json(name = "requirescontact") val requiresContact: Boolean?, // ToDo: expected non null
    @Json(name = "contactrequests") val contactRequests: List<ContactRequest>?,
    val conversations: List<MemberConversation>?
)
