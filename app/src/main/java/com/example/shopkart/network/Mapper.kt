package com.example.shopkart.network

import com.example.shopkart.database.*
import com.example.shopkart.model.AccountModel
import com.example.shopkart.model.CartModel
import com.example.shopkart.model.ItemTypeModel
import com.example.shopkart.model.KitTypeModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkKitContainer(val kits: List<KitType>)

@JsonClass(generateAdapter = true)
data class NetworkItemContainer(val items: List<ItemType>)


@JsonClass(generateAdapter = true)
data class KitType(
    val id: String,
    var name: String,
    @Json(name="image") val imgSrcUrl: String,
    val description: String,
    val price: String
)

@JsonClass(generateAdapter = true)
data class ItemType (
    val id: String,
    @Json(name= "item_name") val name: String,
    @Json(name = "item_image") val imgSrcUrl: String,
    val price: String
)

//type to database
fun List<KitType>.asDatabaseKitType(): List<DatabaseKitType> {
    return map {
        DatabaseKitType(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            description = it.description,
            price = it.price
        )
    }
}
fun List<ItemType>.asDatabaseItemType1(): List<DatabaseItemType1> {
    return map {
        DatabaseItemType1(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}
fun List<ItemType>.asDatabaseItemType2(): List<DatabaseItemType2> {
    return map {
        DatabaseItemType2(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}
fun List<ItemType>.asDatabaseItemType3(): List<DatabaseItemType3> {
    return map {
        DatabaseItemType3(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}

//for database to model(domain)
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
}
fun List<DatabaseItemType1>.asDomainItemType1Model(): List<ItemTypeModel> {
    return map {
        ItemTypeModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}
fun List<DatabaseItemType2>.asDomainItemType2Model(): List<ItemTypeModel> {
    return map {
        ItemTypeModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}
fun List<DatabaseItemType3>.asDomainItemType3Model(): List<ItemTypeModel> {
    return map {
        ItemTypeModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}

//FOR CART::
fun DatabaseKitType.asDatabaseCartType(): DatabaseCart{
    return DatabaseCart(
        id = id,
        name = name,
        imgSrcUrl = imgSrcUrl,
        price = price
    )
}
fun List<DatabaseCart>.asDomainCartModel(): List<CartModel>{
    return map {
        CartModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}

//For Account
fun List<DatabaseAccount>.asDomainAccountModel(): List<AccountModel>{
    return map {
        AccountModel(
            id = it.id,
            price = it.price
        )
    }
}