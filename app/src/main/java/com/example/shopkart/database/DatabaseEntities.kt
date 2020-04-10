package com.example.shopkart.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entities
//For ItemType
@Entity
data class DatabaseItemType1 constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val imgSrcUrl: String,
    val price: String)
@Entity
data class DatabaseItemType2 constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val imgSrcUrl: String,
    val price: String)
@Entity
data class DatabaseItemType3 constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val imgSrcUrl: String,
    val price: String)

//For KitType
@Entity
data class DatabaseKitType constructor(
    @PrimaryKey
    val id: String,
    var name: String,
    val imgSrcUrl: String,
    val description: String,
    val price: String)


//For cart
@Entity
data class DatabaseCart constructor(
    @PrimaryKey
    val name: String,
    val imgSrcUrl: String,
    val price: String)