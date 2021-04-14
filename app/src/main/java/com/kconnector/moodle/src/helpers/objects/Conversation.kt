package com.kconnector.moodle.src.helpers.objects

import com.squareup.moshi.Json
import com.kconnector.moodle.src.async.AsyncMoodleAPI
import com.kconnector.moodle.src.async.Promise
import com.kconnector.moodle.src.helpers.enums.MoodleMessageType
import com.kconnector.moodle.src.requests.core.message.CMSendMessagesToConversationRequest

data class Conversation(
    @Transient var moodleAPI: AsyncMoodleAPI? = null,
    val id: Int,
    @Json(name = "name") val name: String?,
    @Json(name = "subname") val subName: String?,
    @Json(name = "imageurl") val imageURL: String?,
    @Json(name = "type") val type: MoodleMessageType,
    @Json(name = "membercount") val memberCount: Int,
    @Json(name = "ismuted") val isMuted: Boolean,
    @Json(name = "isfavourite") val isFavourite: Boolean,
    @Json(name = "isread") val isRead: Boolean,
    @Json(name = "unreadcount") val unreadMessagesCount: Int?,
    val members: List<ConversationMember>,
    val messages: List<ConversationMessage>,
    @Json(name = "candeletemessagesforallusers") val canDeleteMessagesForAllUsers: Boolean?
) {
    fun sendMessages(vararg messages: ConversationMessageToSend): Promise<MessageToSendResponse> {
        return sendMessages(messages.toList())
    }

    fun sendMessages(messages: List<ConversationMessageToSend>): Promise<MessageToSendResponse> {
        return moodleAPI!!.request(CMSendMessagesToConversationRequest(id, messages.toList()))
    }

    fun sendMessages(vararg messages: String): Promise<MessageToSendResponse> {
        val messagesToSend = mutableListOf<ConversationMessageToSend>()
        for (message in messages) {
            messagesToSend.add(ConversationMessageToSend(message))
        }

        return sendMessages(messagesToSend)
    }
}
