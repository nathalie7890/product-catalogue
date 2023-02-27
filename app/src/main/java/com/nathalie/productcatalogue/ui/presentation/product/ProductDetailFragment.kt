package com.nathalie.productcatalogue.ui.presentation.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nathalie.productcatalogue.R
import com.nathalie.productcatalogue.data.api.RetrofitClient
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.databinding.FragmentProductDetailBinding
import com.nathalie.productcatalogue.ui.presentation.BaseFragment
import com.nathalie.productcatalogue.ui.viewModel.ProductDetailViewModel

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {
    override val viewModel: ProductDetailViewModel by viewModels {
        ProductDetailViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun getLayoutResource() = R.layout.fragment_product_detail

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        val navArgs: ProductDetailFragmentArgs by navArgs()
        viewModel.getProductById(navArgs.id)

        binding?.run {
            btnEdit.setOnClickListener {
                val action =
                    ProductDetailFragmentDirections.actionProductDetailToEditProduct(navArgs.id)
                NavHostFragment.findNavController(this@ProductDetailFragment).navigate(action)
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        viewModel.product.observe(viewLifecycleOwner) {
            binding.run {
                val price = "RM ${it.price}"
                val brand = "Brand: ${it.brand}"
                val rating = "Rating: ${it.rating}"
                this!!.tvTitle.text = it.title
                tvPrice.text = price
                tvDesc.text = it.description
                tvBrand.text = brand
                tvRating.text = rating

                Glide.with(requireContext())
                    .load(it.thumbnail)
                    .placeholder(R.drawable.no_image_found)
                    .into(ivImg)
            }
        }
    }
}