package com.nathalie.productcatalogue.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nathalie.productcatalogue.MyApplication
import com.nathalie.productcatalogue.R
import com.nathalie.productcatalogue.data.api.RetrofitClient
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.databinding.FragmentHomeBinding
import com.nathalie.productcatalogue.ui.adapter.ProductAdapter
import com.nathalie.productcatalogue.ui.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ProductAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        viewModel.products.observe(viewLifecycleOwner) {
            adapter.setProducts(it)
        }
    }

    fun setupAdapter() {
        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        adapter = ProductAdapter(mutableListOf())
        adapter.listener = object : ProductAdapter.Listener {
            override fun onClick(item: Product) {
                val action = HomeFragmentDirections.actionHomeToDetail(item.id)
                NavHostFragment.findNavController(this@HomeFragment).navigate(action)
            }
        }

        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = layoutManager
    }
}