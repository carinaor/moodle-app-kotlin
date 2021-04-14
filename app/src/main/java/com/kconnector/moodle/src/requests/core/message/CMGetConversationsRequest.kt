package com.kconnector.moodle.src.requests.core.message

import com.kconnector.moodle.src.async.AsyncMoodleAPI
import com.kconnector.moodle.src.helpers.objects.Conversation
import com.kconnector.moodle.src.requests.Request
import com.kconnector.moodle.src.helpers.serializer.JSONSerializer
import org.checkerframework.common.value.qual.IntRange

class CMGetConversationsRequest(
    private val userId: @IntRange(from = 0) Int,
    private val limitFrom: @IntRange(from = 0) Int = -1,
    private val limitNumber: @IntRange(from = 0) Int = -1,
    private val type: String? = null,
    private val onlyFavourites: Boolean? = null,
    private val mergeSelf: Boolean? = null
) : Request {


    override fun write(data: MutableMap<String, Any>) {
        data["userid"] = userId
        if (limitFrom > 0) {
            data["limitfrom"] = limitFrom
        }
        if (limitNumber > 0) {
            data["limitnum"] = limitNumber
        }
        if (type != null) {
            data[type] = type
        }
        if (onlyFavourites != null) {
            data["favourites"] = onlyFavourites
        }
        if (mergeSelf != null) {
            data["mergeself"] = mergeSelf
        }
    }

    override fun readResponse(moodleAPI: AsyncMoodleAPI, data: Any): List<Conversation> {
        val conversations = mutableListOf<Conversation>()
        for (entry in (data as Map<String, Any>)["conversations"] as List<Map<*, *>>) {
            val conversation = JSONSerializer.CONVERSATION_ADAPTER.fromJsonValue(entry)!!
            conversation.moodleAPI = moodleAPI
            conversations.add(conversation)
        }
        return conversations.toList()
    }
}
