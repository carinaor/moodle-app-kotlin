package com.kconnector.moodle.src.async

interface PromiseValidator {
    fun matches(data: Any): Boolean
}
