package com.example.ecomerce.viewmodels

import androidx.core.util.rangeTo
import androidx.lifecycle.ViewModel
import com.example.ecomerce.models.Item
import kotlin.random.Random

class AddItemVM : ViewModel(){

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

    fun createItem():Item{
        val id = Random.nextInt(1001,2000)
        val item = Item(
            id= id,
            name = name,
            price = price,
            description = description
        )
        return item
    }
}