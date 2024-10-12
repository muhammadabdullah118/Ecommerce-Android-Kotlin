package com.example.ecomerce.models

data class Customer(
    val id : Int? = null,
    val firstName : String?= null,
    val lastName : String? = null,
    val email : String?= null,
    val phone : String? = null,
    val password : String?= null,
    val role : String? = null
)
