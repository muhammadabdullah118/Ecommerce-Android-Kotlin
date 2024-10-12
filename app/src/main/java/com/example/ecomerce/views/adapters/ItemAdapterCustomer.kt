package com.example.ecomerce.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomerce.R
import com.example.ecomerce.listeners.CardClick
import com.example.ecomerce.models.Item

class ItemAdapterCustomer(
    val context : Context,
    val itemList: List<Item>,
    val listener : CardClick
): RecyclerView.Adapter<ItemAdapterCustomer.ViewHolder>() {

    class ViewHolder(val view : View): RecyclerView.ViewHolder(view){
        val imageIcon = view.findViewById<ImageView>(R.id.cust_img)
        val tvName = view.findViewById<TextView>(R.id.tv_itemName)
        val tvPrice = view.findViewById<TextView>(R.id.tv_itemPrice)
        val cardView = view.findViewById<CardView>(R.id.customerCV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(context).inflate(R.layout.rv_item_customer,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = itemList[position].name
        holder.tvPrice.text = itemList[position].price.toString()
        holder.imageIcon.setImageResource(R.drawable.ic_launcher_background)
        holder.cardView.setOnClickListener{
            listener.onItemClick(itemList[position])
        }
    }
}