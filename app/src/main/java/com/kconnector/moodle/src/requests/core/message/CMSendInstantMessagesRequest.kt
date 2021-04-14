package com.kconnector.moodle.src.requests.core.message

import com.kconnector.moodle.src.async.AsyncMoodleAPI
import com.kconnector.moodle.src.helpers.objects.MessageToSend
import com.kconnector.moodle.src.helpers.objects.MessageToSendResponse
import com.kconnector.moodle.src.requests.Request
import com.kconnector.moodle.src.helpers.serializer.JSONSerializer

class CMSendInstantMessagesRequest(private val messages: List<MessageToSend>) : Request {

    override fun write(data: MutableMap<String, Any>) {
        data["messages"] = messages
    }

    override fun readResponse(moodleAPI: AsyncMoodleAPI, data: Any): List<MessageToSendResponse> {
        val messages = mutableListOf<MessageToSendResponse>()
        for (entry in data as List<Map<*, *>>) {
            messages.add(JSONSerializer.MESSAGE_TO_SEND_RESPONSE_ADAPTER.fromJsonValue(entry)!!)
        }
        return messages.toList()
    }
}
