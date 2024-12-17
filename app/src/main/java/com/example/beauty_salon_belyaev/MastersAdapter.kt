package com.example.beauty_salon_belyaev

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MastersAdapter(private val masters: List<Master>) : RecyclerView.Adapter<MastersAdapter.MasterViewHolder>() {

    class MasterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_master_name)
        val description: TextView = itemView.findViewById(R.id.tv_master_description)
        val service: TextView = itemView.findViewById(R.id.tv_master_service)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_master, parent, false)
        return MasterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MasterViewHolder, position: Int) {
        val master = masters[position]
        holder.name.text = master.fullName
        holder.description.text = master.description
        holder.service.text = master.service
    }

    override fun getItemCount(): Int = masters.size
}