package com.kconnector.moodle.src.async

import com.kconnector.moodle.src.http.HTTP
import com.kconnector.moodle.src.helpers.objects.Conversation
import com.kconnector.moodle.src.helpers.objects.Course
import com.kconnector.moodle.src.helpers.objects.Message
import com.kconnector.moodle.src.helpers.objects.SiteInfo
import com.kconnector.moodle.src.requests.Request
import com.kconnector.moodle.src.requests.block.popularcourses.BlockPopularCoursesGetPopularCoursesRequest
import com.kconnector.moodle.src.requests.core.message.CMGetConversationsRequest
import com.kconnector.moodle.src.requests.core.message.CMGetMessagesRequest
import com.kconnector.moodle.src.requests.core.webservice.CWGetSiteInfo
import java.util.concurrent.Executors

class AsyncMoodleAPI : com.kconnector.moodle.src.AbstractMoodleAPI {
    private val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())!!

    constructor(url: String, accessToken: String) : super(url, accessToken)

    constructor(url: String) : super(url)

    fun <T> request(request: Request, onFullFill: ((T) -> Unit)? = null): Promise<T> {
        val promise = Promise<T>(onFullFill)
        executor.execute {
            promise.fulfill(request.readResponse(this, HTTP.getJson(endpointURL, prepareRequest(request))) as T)
        }
        return promise
    }

    fun requestAllPopularCourses(onFullFill: ((List<Course>) -> Unit)? = null): Promise<List<Course>> {
        return request(BlockPopularCoursesGetPopularCoursesRequest(), onFullFill)
    }

    fun getMessages(userIdTo: Int, onFullFill: ((List<Message>) -> Unit)? = null): Promise<List<Message>> {
        return request(CMGetMessagesRequest(userIdTo), onFullFill)
    }

    fun getAllMessages(onFullFill: ((List<Message>) -> Unit)? = null): Promise<List<Message>> {
        return request(CMGetMessagesRequest(), onFullFill)
    }

    fun getMessages(onFullFill: ((List<Message>) -> Unit)? = null): Promise<List<Message>> {
        return request(CMGetMessagesRequest(siteInfo.userId), onFullFill)
    }

    fun getConversations(onFullFill: ((List<Conversation>) -> Unit)? = null): Promise<List<Conversation>> {
        return request(CMGetConversationsRequest(siteInfo.userId), onFullFill)
    }

    override fun initAPI() {
        this.siteInfo = request<SiteInfo>(CWGetSiteInfo()).waitForFulfill()
    }

    override fun shutdown() {
        executor.shutdown()
    }
}
