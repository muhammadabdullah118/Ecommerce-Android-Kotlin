package com.example.ecomerce.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ecomerce.models.Item
import com.example.ecomerce.utils.ITEM_TABLE

class UpdateItemVM : ViewModel(){

    var item : Item? = null
    var name = ""
    var price = 0
    var description = ""
    var nameError = false
    var priceError = false
    var descError = false

    fun checkInput():Boolean{
        return name.isNotEmpty() && price > 0 && description.isNotEmpty()
    }

    fun checkValues(){
        nameError = name.isEmpty()
        descError = description.isEmpty()
        priceError = price <= 0
    }

    fun updateItem(){

    }
}