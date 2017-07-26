package com.shushper.instagirl

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class GirlsAdapter : RecyclerView.Adapter<GirlsAdapter.GirlsViewHolder>() {

    private var girls: List<Girl> = emptyList()

    fun setGirls(girls: List<Girl>) {
        this.girls = girls
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_girl, parent, false)
        return GirlsViewHolder(view)
    }

    override fun getItemCount(): Int = girls.count()

    override fun onBindViewHolder(holder: GirlsViewHolder, position: Int) {
        holder.bind(girls[position])
    }


    class GirlsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val photo: ImageView by bindView(R.id.photo)
        private val name: TextView by bindView(R.id.name)
        private val likes: TextView by bindView(R.id.likes)

        fun bind(girl: Girl) {
            Picasso.with(itemView.context).load(girl.photo).into(photo)
            name.text = girl.name
            likes.text = "Нравится: ${girl.likes}"
        }
    }
}