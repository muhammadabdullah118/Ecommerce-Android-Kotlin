package com.example.ecomerce.models

data class Order(
    val id : Int?,
    val itemName : String?,
    val totalPrice : Int?,
    val quantity : Int?,
    val status : String?,
    val userId : String?,
    val adminId : String?,
    val address : String?
)
