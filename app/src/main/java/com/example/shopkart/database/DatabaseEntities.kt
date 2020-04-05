package com.example.shopkart.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseItemType constructor(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String)