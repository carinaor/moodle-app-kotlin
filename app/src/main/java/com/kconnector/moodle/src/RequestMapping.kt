package com.kconnector.moodle.src

import com.google.common.collect.HashBiMap
import com.kconnector.moodle.src.requests.Request
import com.kconnector.moodle.src.requests.block.popularcourses.BlockPopularCoursesGetPopularCoursesRequest
import com.kconnector.moodle.src.requests.core.message.*
import com.kconnector.moodle.src.requests.core.webservice.CWGetSiteInfo
import kotlin.reflect.KClass

object RequestMapping {

    private val REQUEST_MAPPING = HashBiMap.create(

        mapOf(

            "block_popularcourses_get_popular_courses" to BlockPopularCoursesGetPopularCoursesRequest::class,

            "core_message_get_messages" to CMGetMessagesRequest::class,

            "core_message_get_conversation_messages" to CMGetConversationMessagesRequest::class,

            "core_webservice_get_site_info" to CWGetSiteInfo::class,

            "core_message_send_instant_messages" to CMSendInstantMessagesRequest::class,

            "core_message_get_conversations" to CMGetConversationsRequest::class,

            "core_message_send_messages_to_conversation" to CMSendMessagesToConversationRequest::class
        )
    )!!
    fun getPacketByFunction(function: String): KClass<out Request>? {
        return REQUEST_MAPPING[function]
    }
    fun getFunctionByPacket(clazz: KClass<out Request>): String? {
        return REQUEST_MAPPING.inverse()[clazz]
    }
    fun registerPacket(function: String, clazz: KClass<out Request>) {
        REQUEST_MAPPING[function] = clazz
    }
}
