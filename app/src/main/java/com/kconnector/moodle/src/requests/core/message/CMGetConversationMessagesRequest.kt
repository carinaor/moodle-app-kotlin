package com.kconnector.moodle.src.requests.core.message

import com.kconnector.moodle.src.async.AsyncMoodleAPI
import com.kconnector.moodle.src.helpers.objects.Message
import com.kconnector.moodle.src.requests.Request
import com.kconnector.moodle.src.helpers.serializer.JSONSerializer
import org.checkerframework.common.value.qual.IntRange

class CMGetConversationMessagesRequest(
    private val currentUserId: @IntRange(from = 0) Int,
    private val conversationId: @IntRange(from = 0) Int,
    private val limitFrom: @IntRange(from = 0) Int = -1,
    private val limitNumber: @IntRange(from = 0) Int = -1,
    private val newestFirst: Boolean? = null,
    private val timeFrom: @IntRange(from = 0) Long = -1
) : Request {


    override fun write(data: MutableMap<String, Any>) {
        data["currentuserid"] = currentUserId
        data["convid"] = conversationId
        if (limitFrom > 0) {
            data["limitfrom"] = limitFrom
        }
        if (limitNumber > 0) {
            data["limitnum"] = limitNumber
        }
        if (newestFirst != null) {
            data["newest"] = newestFirst
        }
        if (timeFrom > 0) {
            data["timefrom"] = timeFrom
        }
    }

    override fun readResponse(moodleAPI: AsyncMoodleAPI, data: Any): List<Message> {
        val messages = mutableListOf<Message>()
        for (entry in (data as Map<String, Any>)["messages"] as List<Map<*, *>>) {
            val message = JSONSerializer.MESSAGE_ADAPTER.fromJsonValue(entry)!!
            message.moodleAPI = moodleAPI
            messages.add(message)
        }
        return messages.toList()
    }
}
