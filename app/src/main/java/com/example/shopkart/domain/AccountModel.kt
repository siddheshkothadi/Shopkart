package com.example.shopkart.domain

data class AccountModel (
    val id: Int,
    val price: Int
){
    val idText = "Order #$id"
    val priceText = "₹$price"
}