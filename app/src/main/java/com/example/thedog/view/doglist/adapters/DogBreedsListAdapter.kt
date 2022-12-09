package com.example.thedog.view.doglist.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.thedog.R
import com.example.thedog.common.UiUtils
import com.example.thedog.databinding.DogBreedsCellBinding
import com.example.thedog.model.data.Dog

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
open class DogBreedsListAdapter(val onItemClicked: (position: Int) -> Unit) : RecyclerView.Adapter<DogBreedsListAdapter.DogBreedsViewHolder>() {

    var dogBreedsList = ArrayList<Dog>(arrayListOf())

    inner class DogBreedsViewHolder(val binding: DogBreedsCellBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewContainer.apply {
                clipToOutline = true
                outlineProvider = UiUtils.createShapeOutlineProvider(context.resources.getDimension(R.dimen.global_cell_radius))
                setBackgroundColor(Color.parseColor("#e6e6e6"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedsViewHolder {
        val binding = DogBreedsCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogBreedsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogBreedsViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClicked.invoke(position)
        }

        if (dogBreedsList[position].image != null) {
            Glide.with(holder.binding.viewImage.context)
                .load(dogBreedsList[position].image!!.url)
                .centerCrop()
                .placeholder(ColorDrawable(Color.parseColor("#f1f1f1")))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.binding.viewImage)
        }
        holder.binding.viewDogName.text = dogBreedsList[position].name
    }

    override fun getItemCount(): Int {
        return dogBreedsList.size
    }
}