package com.example.ecomerce.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ecomerce.models.Customer
import com.example.ecomerce.models.Item
import com.example.ecomerce.utils.CUSTOMER_DATA
import com.example.ecomerce.utils.CUSTOMER_TABLE
import com.example.ecomerce.utils.ITEM_TABLE
import com.example.ecomerce.utils.Prefrences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.gson.Gson

class CustomerDashBoardVM : ViewModel() {

    var list = listOf<Item>()

    fun fetchItems():List<Item>{
        val itemList = mutableListOf<Item>()
        ITEM_TABLE.addValueEventListener(object : ValueEventListener {
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

    fun fetchCustomer(email: String){
        CUSTOMER_TABLE.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snapshot in dataSnapshot.children) {
                        val value = snapshot.getValue(Customer::class.java)
                        Prefrences.setStringValue(CUSTOMER_DATA,Gson().toJson(value))
                        Log.d("FirebaseData", "Data: $value")
                    }
                } else {
                    Log.d("FirebaseData", "No matching data found")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException() // Handle database error
            }
        })
    }

}