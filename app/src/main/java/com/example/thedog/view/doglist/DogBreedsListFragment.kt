package com.example.thedog.view.doglist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thedog.R
import com.example.thedog.databinding.DogListFragmentBinding
import com.example.thedog.viewmodel.DogBreedsViewModel


/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsListFragment: Fragment(R.layout.dog_list_fragment) {

    private lateinit var binding: DogListFragmentBinding
    private val dogBreedsAdapter = DogBreedsListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DogListFragmentBinding.bind(view)
        val viewModel = ViewModelProvider(this)[DogBreedsViewModel::class.java]

        binding.viewRecylerview.apply {
            adapter = dogBreedsAdapter
            layoutManager = LinearLayoutManager(this@DogBreedsListFragment.context)

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

        // Observe DogList and update the recyclerview accordingly
        viewModel.dogListLiveData.observe(viewLifecycleOwner) { list ->
            if (!list.isNullOrEmpty()) {
                dogBreedsAdapter.apply {
                    dogBreedsList = ArrayList(list)
                    notifyDataSetChanged()
                }
            }
        }
    }
}