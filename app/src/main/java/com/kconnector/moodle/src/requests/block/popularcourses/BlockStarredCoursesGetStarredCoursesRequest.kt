package com.kconnector.moodle.src.requests.block.popularcourses

import com.kconnector.moodle.src.async.AsyncMoodleAPI
import com.kconnector.moodle.src.helpers.objects.Course
import com.kconnector.moodle.src.requests.Request
import com.kconnector.moodle.src.helpers.serializer.JSONSerializer
import org.checkerframework.common.value.qual.IntRange

class BlockPopularCoursesGetPopularCoursesRequest : Request {
    private val limit: Int

    constructor() {
        limit = -1
    }

    constructor(limit: @IntRange(from = 0) Int) {
        this.limit = limit
    }

    override fun write(data: MutableMap<String, Any>) {
        if (limit > 0) {
            data["limit"] = limit
        }
    }

    override fun readResponse(moodleAPI: AsyncMoodleAPI, data: Any): List<Course> {
        val courses = mutableListOf<Course>()
        for (entry in data as List<*>) {
            val course = JSONSerializer.COURSE_ADAPTER.fromJsonValue(entry as Map<*, *>)!!
            course.moodleAPI = moodleAPI
            courses.add(course)
        }
        return courses.toList()
    }
}
