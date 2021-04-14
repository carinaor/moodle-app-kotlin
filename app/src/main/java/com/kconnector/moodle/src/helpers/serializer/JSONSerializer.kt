package com.kconnector.moodle.src.helpers.serializer

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.kconnector.moodle.src.helpers.objects.*

object JSONSerializer {
    private val MOSHI = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(BooleanAdapter())
        .add(MessageFormatAdapter())
        .add(MessageTypeAdapter())
        .add(MemberConversationTypeAdapter())
        .build()!!
    private val OBJECT_ADAPTERS = mutableMapOf<Class<Any>, JsonAdapter<Any>>()

    val JSON_MAP_ADAPTER: JsonAdapter<Map<String, Any>> = MOSHI.adapter(Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java))
    val JSON_LIST_ADAPTER: JsonAdapter<List<Any>> = MOSHI.adapter(Types.newParameterizedType(List::class.java, Any::class.java))

    val COURSE_ADAPTER = MOSHI.adapter(Course::class.java)!!
    val SITE_INFO_ADAPTER = MOSHI.adapter(SiteInfo::class.java)!!
    val MESSAGE_ADAPTER = MOSHI.adapter(Message::class.java)!!
    val MESSAGE_TO_SEND_RESPONSE_ADAPTER = MOSHI.adapter(MessageToSendResponse::class.java)!!
    val CONVERSATION_ADAPTER = MOSHI.adapter(Conversation::class.java)!!
    val CONVERSATION_MESSAGE_TO_SEND_RESPONSE_ADAPTER = MOSHI.adapter(ConversationMessage::class.java)!!

    fun objectToMap(value: Any): Map<String, Any> {
        var adapter = OBJECT_ADAPTERS[value.javaClass]
        if (adapter == null) {
            adapter = MOSHI.adapter(value.javaClass)!!
            OBJECT_ADAPTERS[value.javaClass] = adapter
        }
        return adapter.toJsonValue(value) as Map<String, Any>
    }
}
