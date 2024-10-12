package com.example.ecomerce.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

//Roles
const val CUSTOMER = "customer"
const val ADMIN = "admin"
val AUTH = FirebaseAuth.getInstance()
val database = FirebaseDatabase.getInstance()
val CUSTOMER_TABLE = database.getReference("customer_table")
val ITEM_TABLE = database.getReference("item_table")
val ORDER_TABLE = database.getReference("order_table")
val ADDRESS_TABLE = database.getReference("address_table")
const val ROLE = "role"
const val CUSTOMER_DATA= "customer_data"