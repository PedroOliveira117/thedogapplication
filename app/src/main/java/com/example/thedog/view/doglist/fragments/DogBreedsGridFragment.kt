package com.example.thedog.view.doglist.fragments

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.thedog.view.doglist.adapters.DogBreedsGridAdapter
import com.example.thedog.view.doglist.adapters.DogBreedsListAdapter

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsGridFragment: DogBreedsListFragment() {

    override fun generateAdapter(onItemClicked: (position: Int) -> Unit): DogBreedsListAdapter = DogBreedsGridAdapter(onItemClicked)
    override fun generateLayoutManager(): LayoutManager = GridLayoutManager(context, 2)
}