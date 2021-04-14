package com.kconnector.moodle.src.helpers.enums

enum class MoodleMessageType {
    @Deprecated("Does not exist, used because of enum ordinal")
    NONE,
    INDIVIDUAL,
    GROUP,
    SELF;

    companion object {
        val VALUES = values()
    }
}
