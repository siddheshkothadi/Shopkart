package com.example.shopkart.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entities
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
@Entity
data class DatabaseKitType constructor(
    @PrimaryKey
    val id: String,
    var name: String,
    val imgSrcUrl: String,
    val description: String,
    val price: String)


/*
//functions that maps database to respective domain entities
fun List<DatabaseItemType>.asDomainItemTypeModel(): List<ItemTypeModel> {
    return map {
        ItemTypeModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}
fun List<DatabaseKitType>.asDomainKitTypeModel(): List<KitTypeModel> {
    return map {
        KitTypeModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            description = it.description,
            price = it.price
        )
    }
}*/