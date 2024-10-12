package com.example.ecomerce.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ecomerce.models.Item

class OrderPlaceVM( ): ViewModel() {

    var item : Item?= null
    var quantity = 1
    var price = 0
    var totalPrice = 0


    fun addPrice(){
        quantity= quantity+1
        totalPrice = quantity * price
    }

    fun subPrice(){
        if(quantity>1){
            quantity = quantity-1
            totalPrice = quantity * price
        }
    }

}