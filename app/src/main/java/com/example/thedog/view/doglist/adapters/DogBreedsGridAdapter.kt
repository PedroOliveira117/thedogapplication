package com.example.thedog.view.doglist.adapters

import android.view.ViewGroup
import com.example.thedog.R

/**
 * Created by pedrooliveira on 08/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsGridAdapter: DogBreedsListAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedsViewHolder {
        return super.onCreateViewHolder(parent, viewType).apply {
            imageView.layoutParams.height = parent.context.resources.getDimension(R.dimen.dog_breeds_grid_cell_image_height).toInt()
        }
    }
}