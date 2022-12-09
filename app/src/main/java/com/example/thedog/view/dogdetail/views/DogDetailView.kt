package com.example.thedog.view.dogdetail.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.thedog.R
import com.example.thedog.common.UiUtils
import com.example.thedog.databinding.DogDetailViewBinding
import com.example.thedog.model.data.*

/**
 * Created by pedrooliveira on 09/12/2022
 * All rights reserved GoodBarber
 */
class DogDetailView: RelativeLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    val binding: DogDetailViewBinding = DogDetailViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.viewDescriptionContainer.apply {
            clipToOutline = true
            outlineProvider = UiUtils.createShapeOutlineProvider(context.resources.getDimension(R.dimen.global_cell_radius))
            setBackgroundColor(Color.parseColor("#e6e6e6"))
        }
    }

    fun initUI(dog: Dog) {
        binding.apply {

            // Dog Breed Name
            viewDogBreedName.text = dog.breedNameDisplay()

            // Dog Breed Group
            viewDogBreedGroup.text = dog.breedGroupDisplay()

            // Dog Origin
            viewDogOrigin.text = dog.originDisplay()

            // Dog Temperament
            viewDogBreedTemperament.text = dog.temperamentDisplay()
        }
    }
}