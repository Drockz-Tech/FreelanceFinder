// src/main/java/com/example/freelancefinder/FreelancerAdapter.kt
package com.example.freelancefinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide

data class Freelancer(val name: String, val profession: String, val imageUrl: String)

class FreelancerAdapter(private val freelancers: List<Freelancer>) :
    RecyclerView.Adapter<FreelancerAdapter.FreelancerViewHolder>() {

    class FreelancerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        val professionTextView: TextView = itemView.findViewById(R.id.profession_text_view)
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreelancerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_freelancer, parent, false)
        return FreelancerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FreelancerViewHolder, position: Int) {
        val freelancer = freelancers[position]
        holder.nameTextView.text = freelancer.name
        holder.professionTextView.text = freelancer.profession
        //Glide.with(holder.itemView.context).load(freelancer.imageUrl).into(holder.imageView)
    }

    override fun getItemCount() = freelancers.size
}

