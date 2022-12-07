package com.example.thedog.view.doglist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.thedog.R
import com.example.thedog.common.UiUtils
import com.example.thedog.model.data.Dog

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsListAdapter: RecyclerView.Adapter<DogBreedsListAdapter.DogBreedsViewHolder>() {

    var dogBreedsList = ArrayList<Dog>(arrayListOf())

    inner class DogBreedsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val containerView: RelativeLayout
        val imageView: ImageView
        val titleView: TextView

        init {
            containerView = view.findViewById(R.id.view_container)
            imageView = view.findViewById(R.id.view_image)
            titleView = view.findViewById(R.id.view_title)

            containerView.apply {
                clipToOutline = true
                outlineProvider = UiUtils.createShapeOutlineProvider(view.context.resources.getDimension(R.dimen.dog_breeds_cell_radius))
                setBackgroundColor(Color.parseColor("#e6e6e6"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dog_breeds_cell, parent, false)
        return DogBreedsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogBreedsViewHolder, position: Int) {
        Glide.with(holder.imageView.context)
            .load(dogBreedsList[position].image.url)
            .centerCrop()
            .placeholder(ColorDrawable(Color.parseColor("#f1f1f1")))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageView)
        holder.titleView.text = dogBreedsList[position].name
    }

    override fun getItemCount(): Int {
        return dogBreedsList.size
    }

}