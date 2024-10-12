package com.example.ecomerce.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Item(
    val id : Int = 0,
    val name : String = "",
    val price : Int = 0,
    val description : String = ""
)