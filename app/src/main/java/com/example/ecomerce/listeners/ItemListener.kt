package com.example.ecomerce.listeners

import com.example.ecomerce.models.Item

interface ItemListener {
    fun updateClick(item : Item)
    fun onItemClick(item : Item)
}