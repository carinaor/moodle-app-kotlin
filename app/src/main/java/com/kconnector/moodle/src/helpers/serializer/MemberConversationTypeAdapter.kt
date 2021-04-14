package com.kconnector.moodle.src.helpers.serializer

import com.squareup.moshi.*
import com.kconnector.moodle.src.helpers.enums.MoodleMemberConversationType


class MemberConversationTypeAdapter : JsonAdapter<MoodleMemberConversationType>() {

    @ToJson
    override fun toJson(writer: JsonWriter, value: MoodleMemberConversationType?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value.ordinal)
    }

    @FromJson
    override fun fromJson(reader: JsonReader): MoodleMemberConversationType? {
        return when (reader.peek()) {
            JsonReader.Token.NULL -> reader.nextNull()
            JsonReader.Token.NUMBER -> {
                MoodleMemberConversationType.VALUES[reader.nextInt()]
            }
            JsonReader.Token.STRING -> MoodleMemberConversationType.valueOf(reader.nextString())
            else -> throw IllegalArgumentException("Not a member conversation type!")
        }
    }
}
