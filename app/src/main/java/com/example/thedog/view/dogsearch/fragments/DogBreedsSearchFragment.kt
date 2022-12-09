package com.example.thedog.view.dogsearch.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thedog.R
import com.example.thedog.common.NavigationUtils
import com.example.thedog.common.UiUtils
import com.example.thedog.databinding.DogBreedsSearchFragmentBinding
import com.example.thedog.view.dogsearch.adapters.DogBreedsSearchListAdapter
import com.example.thedog.viewmodel.DogBreedSearchViewModel

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsSearchFragment : Fragment(R.layout.dog_breeds_search_fragment) {

    private lateinit var binding: DogBreedsSearchFragmentBinding
    private lateinit var dogBreedsSearchAdapter: DogBreedsSearchListAdapter
    private val viewModel: DogBreedSearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DogBreedsSearchFragmentBinding.bind(view)

        binding.apply {
            viewSearch.apply {
                setBackgroundColor(Color.parseColor("#e6e6e6"))
                clipToOutline = true
                outlineProvider = UiUtils.createShapeOutlineProvider(view.context.resources.getDimension(R.dimen.global_cell_radius))
                clearFocus()

                setOnQueryTextListener(object : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (!query.isNullOrEmpty()) {
                            viewModel.searchDogBreed(query)
                        }
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                })
            }

            viewRecylerview.apply {
                dogBreedsSearchAdapter = DogBreedsSearchListAdapter { position ->
                    viewModel.dogSearchListLiveData.value?.get(position).let { dog ->
                        if (dog != null) {
                            NavigationUtils.navigateToDogDetail(requireActivity(), dog.id.toString())
                        }
                    }
                }
                adapter = dogBreedsSearchAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        viewModel.apply {
            // Observe is Loading to display progress bar
            isLoadingLiveData.observe(viewLifecycleOwner) {  isLoading ->
                binding.apply {
                    if (isLoading) {
                        viewProgressBar.visibility = View.VISIBLE
                        viewRecylerview.visibility = View.GONE
                        viewSearchEmpty.visibility = View.GONE
                    } else {
                        viewProgressBar.visibility = View.GONE
                    }
                }
            }

            // Observe searchList to show list of results. If no result found show empty result screen
            dogSearchListLiveData.observe(viewLifecycleOwner) { list ->
                binding.apply {
                    if (!list.isNullOrEmpty()) {
                        viewRecylerview.visibility = View.VISIBLE
                    } else if (list.isNullOrEmpty() && query.isNotEmpty()) {
                        viewRecylerview.visibility = View.GONE
                        viewSearchEmpty.visibility = View.VISIBLE
                        viewSearchEmpty.binding.viewSearchNotFound.text = resources.getText(R.string.dog_breeds_search_no_result).toString().plus(" $query")
                    }
                }

                // Update recyclerview with data received
                dogBreedsSearchAdapter.apply {
                    dogBreedsSearchList = ArrayList(list)
                    notifyDataSetChanged()
                }
            }
        }
    }
}