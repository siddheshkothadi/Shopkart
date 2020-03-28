package com.example.tabbedactivity.network

import com.squareup.moshi.Json

data class Child (
    val id: String,
    @Json(name="image") val imgSrcUrl: String
)