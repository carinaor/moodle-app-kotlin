package com.kconnector.moodle.src.requests.core.message

import com.kconnector.moodle.src.async.AsyncMoodleAPI
import com.kconnector.moodle.src.helpers.objects.Message
import com.kconnector.moodle.src.requests.Request
import com.kconnector.moodle.src.helpers.serializer.JSONSerializer
import org.checkerframework.common.value.qual.IntRange

class CMGetMessagesRequest : Request {
    private val userIdTo: Int

    constructor() {
        userIdTo = 0
    }

    constructor(userIdTo: @IntRange(from = 0) Int) {
        this.userIdTo = userIdTo
    }

    override fun write(data: MutableMap<String, Any>) {
        data["useridto"] = userIdTo
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
