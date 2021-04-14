package com.mymoodle.application.loader

import java.util.ArrayList
import java.util.HashMap
import com.kconnector.moodle.src.async.AsyncMoodleAPI

object ListContent {
    private lateinit var myAPI: AsyncMoodleAPI
    val ITEMS: MutableList<Item> = ArrayList()

    val ITEM_MAP: MutableMap<String, Item> = HashMap()

    //private val COUNT = 25

    init {
        //myAPI = AsyncMoodleAPI("localhost:8080/moodle_api", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
        myAPI = AsyncMoodleAPI("localhost:8080/moodle_api")
        myAPI.login("admin", "admin1234")
        myAPI.initAPI()
        myAPI.requestAllPopularCourses() {
            // loop over all courses
            var i = 0
            for (course in it) {
                addItem(createItem(i, course.fullName))
                i++
            }

            // because we fetched all data, we want to stop everything (e.g clean up and stop executor service). After that the API won't function anymore
            myAPI.shutdown()
        }
        // Add some sample items.
        /*for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }*/

    }

    private fun addItem(item: Item) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createItem(position: Int, content: String): Item {
        return Item(position.toString(), content, makeDetails(content))
    }

    private fun makeDetails(content: String): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(content)
        //for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        //}
        return builder.toString()
    }

    data class Item(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}