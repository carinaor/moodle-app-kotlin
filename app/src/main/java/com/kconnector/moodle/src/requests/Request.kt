package com.kconnector.moodle.src.requests

import com.kconnector.moodle.src.async.AsyncMoodleAPI

interface Request {
    fun write(data: MutableMap<String, Any>)
    fun readResponse(moodleAPI: AsyncMoodleAPI, data: Any): Any
}
