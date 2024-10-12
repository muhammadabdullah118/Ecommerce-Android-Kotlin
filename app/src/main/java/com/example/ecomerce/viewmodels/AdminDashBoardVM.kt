package com.example.ecomerce.viewmodels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.ecomerce.models.Item
import com.example.ecomerce.utils.ITEM_TABLE
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class AdminDashBoardVM : ViewModel() {

    var list = listOf<Item>()

    fun fetchItems():List<Item>{
        val itemList = mutableListOf<Item>()
        ITEM_TABLE.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(items in snapshot.children){
                    val item = items.getValue<Item>()
                    if (item != null){
                        itemList.add(item)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(context , "Failed to load data.", Toast.LENGTH_SHORT).show()
            }

        })
        return itemList
    }
}