package com.kconnector.moodle.src.http

import com.kconnector.moodle.src.helpers.exceptions.InternalServerErrorException
import com.kconnector.moodle.src.helpers.exceptions.MoodleAPIErrorException
import com.kconnector.moodle.src.helpers.exceptions.UnknownHTTPResponseException
import com.kconnector.moodle.src.helpers.serializer.JSONSerializer
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


object HTTP {

    @Throws(IOException::class, InterruptedException::class)
    fun get(url: String): HttpResponse<String> {
        return HttpClient.newHttpClient().send(HttpRequest.newBuilder().uri(URI.create(url)).build(), HttpResponse.BodyHandlers.ofString())
    }

    private fun urlEncodeUTF8(s: String): String {
        return try {
            URLEncoder.encode(s, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            throw UnsupportedOperationException(e)
        }
    }

    private fun urlEncodeUTF8(data: Map<String, Any>): String {
        val builder = StringBuilder()
        for ((key, value) in data) {
            if (builder.isNotEmpty()) {
                builder.append("&")
            }
            builder.append(String.format("%s=%s", urlEncodeUTF8(key), urlEncodeUTF8(value.toString())))
        }
        return builder.toString()
    }

    @Throws(IOException::class, InterruptedException::class)
    fun get(url: String, data: Map<String, Any>): HttpResponse<String> {
        return get(url + "?" + urlEncodeUTF8(data))
    }

    @Throws(IOException::class, InterruptedException::class)
    fun getJson(url: String, data: Map<String, Any>): Any {
        val response = get(url, data)
        if (response.statusCode() == 500) {
            throw InternalServerErrorException()
        }
        if (response.statusCode() != 200) {
            throw UnknownHTTPResponseException()
        }
        val rawJson = response.body()!!

        if (rawJson.startsWith('[')) {
            return JSONSerializer.JSON_LIST_ADAPTER.fromJson(rawJson)!!
        }
        val json = JSONSerializer.JSON_MAP_ADAPTER.fromJson(rawJson)!!
        if (json.containsKey("errorcode")) {
            throw MoodleAPIErrorException(json["message"]!! as String)
        }
        return json
    }
}
