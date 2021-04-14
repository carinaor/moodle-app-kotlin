package com.kconnector.moodle.src.requests.core.webservice

import com.kconnector.moodle.src.async.AsyncMoodleAPI
import com.kconnector.moodle.src.helpers.objects.SiteInfo
import com.kconnector.moodle.src.requests.Request
import com.kconnector.moodle.src.helpers.serializer.JSONSerializer

class CWGetSiteInfo : Request {

    override fun write(data: MutableMap<String, Any>) {
    }

    override fun readResponse(moodleAPI: AsyncMoodleAPI, data: Any): SiteInfo {
        return JSONSerializer.SITE_INFO_ADAPTER.fromJsonValue(data as Map<*, *>)!!
    }
}
