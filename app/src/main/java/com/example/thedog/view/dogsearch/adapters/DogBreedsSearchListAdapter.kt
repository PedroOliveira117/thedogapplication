package com.example.thedog.view.dogsearch.adapters

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thedog.R
import com.example.thedog.common.UiUtils
import com.example.thedog.databinding.DogBreedsSearchCellBinding
import com.example.thedog.model.data.*

/**
 * Created by pedrooliveira on 08/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsSearchListAdapter(val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<DogBreedsSearchListAdapter.DogBreedsSearchViewHolder>() {

    var dogBreedsSearchList = ArrayList<Dog>(arrayListOf())

    inner class DogBreedsSearchViewHolder(val binding: DogBreedsSearchCellBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewContainer.apply {
                clipToOutline = true
                outlineProvider = UiUtils.createShapeOutlineProvider(context.resources.getDimension(R.dimen.global_cell_radius))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedsSearchViewHolder {
        val binding = DogBreedsSearchCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogBreedsSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogBreedsSearchViewHolder, position: Int) {
        val dog = dogBreedsSearchList[position]

        holder.itemView.setOnClickListener {
            onItemClicked.invoke(position)
        }

        holder.binding.apply {

            // Dog Breed
            viewDogBreedName.apply {
                val breedNameTitle = resources.getText(R.string.dog_breed_name).toString()
                val breedName = dog.breedNameDisplay(context)
                text = applyBoldToText(breedNameTitle.plus(" $breedName"), breedNameTitle)
            }

            // Dog Breed Group
            viewDogBreedGroup.apply {
                val breedGroupTitle = resources.getText(R.string.dog_breed_group).toString()
                val breedGroup = dog.breedGroupDisplay(context)
                text = applyBoldToText(breedGroupTitle.plus(" $breedGroup"), breedGroupTitle)
            }

            // Dog Breed Origin
            viewDogBreedOrigin.apply {
                val dogOriginTitle = resources.getText(R.string.dog_origin).toString()
                val dogOrigin = dog.originDisplay(context)
                text = applyBoldToText(dogOriginTitle.plus(" $dogOrigin"), dogOriginTitle)
            }

            // Dog Breed Temperament
            viewDogBreedTemperament.apply {
                val dogTemperamentTitle = resources.getText(R.string.dog_temperament).toString()
                val dogTemperament = dog.temperamentDisplay(context)
                text = applyBoldToText(dogTemperamentTitle.plus(" $dogTemperament"), dogTemperamentTitle)
            }
        }
    }

    /**
     * Apply bold to a specific string inside a string if exists
     * */
    private fun applyBoldToText(text: String, textToApplyBold: String): SpannableStringBuilder {
        val textSpan = SpannableStringBuilder(text)
        val textToApplyBoldIndex = textSpan.indexOf(textToApplyBold)

        if (textToApplyBold.isNotEmpty()) {
            textSpan.setSpan(StyleSpan(Typeface.BOLD), textToApplyBoldIndex, textToApplyBoldIndex + textToApplyBold.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return textSpan
    }

    override fun getItemCount(): Int {
        return dogBreedsSearchList.size
    }
}