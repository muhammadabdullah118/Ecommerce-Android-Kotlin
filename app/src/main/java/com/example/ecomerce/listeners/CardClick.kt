package com.example.ecomerce.listeners

import com.example.ecomerce.models.Item


interface CardClick {
    fun onItemClick(item : Item)
}