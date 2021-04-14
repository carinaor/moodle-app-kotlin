package com.kconnector.moodle.src.requests.core.message

import com.kconnector.moodle.src.async.AsyncMoodleAPI
import com.kconnector.moodle.src.helpers.objects.ConversationMessage
import com.kconnector.moodle.src.helpers.objects.ConversationMessageToSend
import com.kconnector.moodle.src.requests.Request
import com.kconnector.moodle.src.helpers.serializer.JSONSerializer

class CMSendMessagesToConversationRequest(
    private val conversationId: Int,
    private val messages: List<ConversationMessageToSend>
) : Request {

    override fun write(data: MutableMap<String, Any>) {
        data["conversationid"] = conversationId
        data["messages"] = messages
    }

    override fun readResponse(moodleAPI: AsyncMoodleAPI, data: Any): List<ConversationMessage> {
        val messages = mutableListOf<ConversationMessage>()
        for (entry in data as List<Map<*, *>>) {
            messages.add(JSONSerializer.CONVERSATION_MESSAGE_TO_SEND_RESPONSE_ADAPTER.fromJsonValue(entry)!!)
        }
        return messages.toList()
    }
}
