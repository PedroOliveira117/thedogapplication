package com.example.thedog.view.dogsearch.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.thedog.databinding.DogBreedsSearchEmptyViewBinding

/**
 * Created by pedrooliveira on 08/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsSearchEmptyView: RelativeLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    val binding: DogBreedsSearchEmptyViewBinding = DogBreedsSearchEmptyViewBinding.inflate(LayoutInflater.from(context), this, true)

}