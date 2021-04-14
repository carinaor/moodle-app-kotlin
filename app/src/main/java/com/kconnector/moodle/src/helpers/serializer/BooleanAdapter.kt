package com.kconnector.moodle.src.helpers.serializer

import com.squareup.moshi.*


class BooleanAdapter : JsonAdapter<Boolean>() {

    @ToJson
    override fun toJson(writer: JsonWriter, value: Boolean?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(if (value) 0x01 else 0x00)
    }

    @FromJson
    override fun fromJson(reader: JsonReader): Boolean? {
        return when (reader.peek()) {
            JsonReader.Token.NULL -> reader.nextNull()
            JsonReader.Token.NUMBER -> {
                val value = reader.nextInt()
                if (value == 0x01) {
                    return true
                }
                if (value == 0x00) {
                    return false
                }
                throw IllegalArgumentException("Not a boolean: $value")
            }
            JsonReader.Token.BOOLEAN -> reader.nextBoolean()
            JsonReader.Token.STRING -> {
                val value = reader.nextString()
                if (value == "true") {
                    return true
                }
                if (value == "false") {
                    return false
                }
                throw IllegalArgumentException("Not a boolean: $value")
            }
            else -> throw IllegalArgumentException("Not a boolean!")
        }
    }
}
