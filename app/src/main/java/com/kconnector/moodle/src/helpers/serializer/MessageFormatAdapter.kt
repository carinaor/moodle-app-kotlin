package com.kconnector.moodle.src.helpers.serializer

import com.squareup.moshi.*
import com.kconnector.moodle.src.helpers.enums.MoodleMessageFormat


class MessageFormatAdapter : JsonAdapter<MoodleMessageFormat>() {

    @ToJson
    override fun toJson(writer: JsonWriter, value: MoodleMessageFormat?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value.ordinal)
    }

    @FromJson
    override fun fromJson(reader: JsonReader): MoodleMessageFormat? {
        return when (reader.peek()) {
            JsonReader.Token.NULL -> reader.nextNull()
            JsonReader.Token.NUMBER -> {
                MoodleMessageFormat.VALUES[reader.nextInt()]
            }
            JsonReader.Token.STRING -> MoodleMessageFormat.valueOf(reader.nextString())
            else -> throw IllegalArgumentException("Not a message format!")
        }
    }
}
