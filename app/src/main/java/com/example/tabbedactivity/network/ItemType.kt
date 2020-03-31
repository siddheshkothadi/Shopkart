package com.example.tabbedactivity.network

import com.squareup.moshi.Json

data class ItemType (
    val id: String,
    @Json(name= "item_name") val name: String,
    @Json(name = "item_image") val imgSrcUrl: String,
    val price: String
)