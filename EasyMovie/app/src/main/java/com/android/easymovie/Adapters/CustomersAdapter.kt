package com.android.easymovie.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.easymovie.Models.CustomersJson
import com.android.easymovie.R

import com.squareup.picasso.Picasso

class CustomersAdapter(var items: List<CustomersJson> ) : RecyclerView.Adapter<CustomersAdapter.CustomersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
        return CustomersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomersViewHolder, position: Int) {
        val customer = items[position]

        holder.bind(customer)
    }

    override fun getItemCount() = items.size

    class CustomersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var imageLogo: ImageView

        init {
            tvName = itemView.findViewById(R.id.tvName)
            imageLogo = itemView.findViewById(R.id.imageLogo)

        }

        fun bind(customer: CustomersJson) {
            tvName.text = customer.name
            Picasso.get()
                .load("${customer.logo}")
                .into(imageLogo)
        }
    }
}