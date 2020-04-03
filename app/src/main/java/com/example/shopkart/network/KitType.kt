package com.example.shopkart.network
import com.squareup.moshi.Json


data class KitType(
    val id: String,
    var name: String,
    @Json(name="image") val imgSrcUrl: String,
    val description: String,
    val price: String
)