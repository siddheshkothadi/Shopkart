package com.example.tabbedactivity.network
import com.squareup.moshi.Json


data class Property(
    val id: String,
    val name: String,
    @Json(name="image") val imgSrcUrl: String,
    val descr: String
)