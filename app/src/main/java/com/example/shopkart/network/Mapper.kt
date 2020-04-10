package com.example.shopkart.network

import com.example.shopkart.database.*
import com.example.shopkart.domain.CartModel
import com.example.shopkart.domain.ItemTypeModel
import com.example.shopkart.domain.KitTypeModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.w3c.dom.ls.LSInput

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
fun List<DatabaseKitType>.asDatabaseCartType(): List<DatabaseCart>{
    return map {
        DatabaseCart(
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )

    }
}
fun List<DatabaseCart>.asDomainCartModel(): List<CartModel>{
    return map {
        CartModel(
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}