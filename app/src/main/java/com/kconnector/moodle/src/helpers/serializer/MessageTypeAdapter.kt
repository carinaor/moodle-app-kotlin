package com.kconnector.moodle.src.helpers.serializer

import com.squareup.moshi.*
import com.kconnector.moodle.src.helpers.enums.MoodleMessageType


class MessageTypeAdapter : JsonAdapter<MoodleMessageType>() {

    @ToJson
    override fun toJson(writer: JsonWriter, value: MoodleMessageType?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value.ordinal)
    }

    @FromJson
    override fun fromJson(reader: JsonReader): MoodleMessageType? {
        return when (reader.peek()) {
            JsonReader.Token.NULL -> reader.nextNull()
            JsonReader.Token.NUMBER -> {
                MoodleMessageType.VALUES[reader.nextInt()]
            }
            JsonReader.Token.STRING -> MoodleMessageType.valueOf(reader.nextString())
            else -> throw IllegalArgumentException("Not a message type!")
        }
    }
}
