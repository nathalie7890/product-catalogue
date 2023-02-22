package com.nathalie.productcatalogue.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nathalie.productcatalogue.R
import com.nathalie.productcatalogue.data.api.RetrofitClient
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.databinding.FragmentProductDetailBinding
import com.nathalie.productcatalogue.ui.viewModel.HomeViewModel
import com.nathalie.productcatalogue.ui.viewModel.ProductDetailViewModel

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels {
        ProductDetailViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navArgs: ProductDetailFragmentArgs by navArgs()
        viewModel.getProductById(navArgs.id)
        viewModel.product.observe(viewLifecycleOwner) {
            binding.run {
                val price = "RM ${it.price}"
                val brand = "Brand: ${it.brand}"
                val rating = "Rating: ${it.rating}"
                tvTitle.text = it.title
                tvPrice.text = price
                tvDesc.text = it.description
                tvBrand.text = brand
                tvRating.text = rating

                if (it.images.isNotEmpty() && URLUtil.isValidUrl(it.images[0])) {
                    Glide.with(binding.root)
                        .load(it.images[0]).into(ivImg)
                } else {
                    Glide.with(binding.root)
                        .load(R.drawable.no_image_found).into(ivImg)
                }
            }
        }
    }
}