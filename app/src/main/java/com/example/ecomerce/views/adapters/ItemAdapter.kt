package com.example.ecomerce.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomerce.R
import com.example.ecomerce.listeners.ItemListener
import com.example.ecomerce.models.Item

class ItemAdapter(
    val context: Context,
    var listItem : List<Item>,
    val listener : ItemListener
): RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    class ViewHolder(val view : View): RecyclerView.ViewHolder(view){
        val cardView = view.findViewById<CardView>(R.id.cvItem)
        val imageIcon = view.findViewById<ImageView>(R.id.imgItem)
        val tvName = view.findViewById<TextView>(R.id.tvItemName)
        val tvPrice = view.findViewById<TextView>(R.id.tvItemPrice)
        val btnUpdate = view.findViewById<TextView>(R.id.tvItemUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rv_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = listItem[position].name
        holder.tvPrice.text = listItem[position].price.toString()
        holder.imageIcon.setImageResource(R.drawable.ic_launcher_background)
        holder.cardView.setOnClickListener{
            listener.onItemClick(listItem[position])
        }
        holder.btnUpdate.setOnClickListener{
            listener.updateClick(listItem[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateSetItemList(newItemList: List<Item>) {
        listItem = newItemList
        notifyDataSetChanged()
    }
}