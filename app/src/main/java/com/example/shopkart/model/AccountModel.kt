package com.example.shopkart.model

data class AccountModel (
    val id: Int,
    val price: Int
){
    val idText = "Order #$id"
    val priceText = "₹$price"
}