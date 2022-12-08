package com.example.thedog.view.doglist.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.thedog.R
import com.example.thedog.databinding.DogBreedsListFragmentBinding
import com.example.thedog.view.doglist.adapters.DogBreedsListAdapter
import com.example.thedog.viewmodel.DogBreedsViewModel


/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
open class DogBreedsListFragment: Fragment(R.layout.dog_breeds_list_fragment) {

    private lateinit var binding: DogBreedsListFragmentBinding
    private lateinit var dogBreedsAdapter: DogBreedsListAdapter
    private val viewModel: DogBreedsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DogBreedsListFragmentBinding.bind(view)

        binding.viewRecylerview.apply {
            dogBreedsAdapter = generateAdapter()
            adapter = dogBreedsAdapter
            layoutManager = generateLayoutManager()

            // Mechanism for loading more dogs
            // When reaching the last 5 items of the list triggers a new request to get more items
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        val recyclerLayout = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                        if (recyclerLayout != null) {
                            val total = recyclerLayout.itemCount
                            val last = recyclerLayout.findLastVisibleItemPosition()
                            if (total > 0 && last + 5 == total) {
                                viewModel.getDogBreeds()
                            }
                        }
                    }
                }
            })
        }

        viewModel.apply {
            // Observe is Loading to display progress bar when list is empty
            isLoadingLiveData.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading && dogListLiveData.value!!.isEmpty()) {
                    binding.viewProgressBar.visibility = View.VISIBLE
                } else {
                    binding.viewProgressBar.visibility = View.GONE
                }
            }

            // Observe DogList and update the recyclerview accordingly
            dogListLiveData.observe(viewLifecycleOwner) { list ->
                if (!list.isNullOrEmpty()) {
                    dogBreedsAdapter.apply {
                        dogBreedsList = ArrayList(list)
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }

    open fun generateLayoutManager(): LayoutManager = LinearLayoutManager(context)

    open fun generateAdapter(): DogBreedsListAdapter = DogBreedsListAdapter()
}