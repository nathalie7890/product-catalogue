package com.nathalie.productcatalogue.ui.presentation.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nathalie.productcatalogue.R
import com.nathalie.productcatalogue.data.api.RetrofitClient
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.ui.presentation.BaseProductFragment
import com.nathalie.productcatalogue.ui.presentation.product.viewModel.AddProductViewModel
import com.nathalie.productcatalogue.ui.presentation.product.viewModel.EditProductViewModel
import kotlinx.coroutines.launch

class EditProductFragment : BaseProductFragment() {
    override val viewModel: EditProductViewModel by viewModels {
        EditProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }


    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        val navArgs: EditProductFragmentArgs by navArgs()
        viewModel.getProductById(navArgs.id)

        viewModel.product.observe(viewLifecycleOwner) {
            binding?.run {
                etTitle.setText(it.title)
                etDesc.setText(it.description)
                etPrice.setText(it.price.toString())
                etDiscount.setText(it.discountPercentage.toString())
                etRating.setText(it.rating.toString())
                etStock.setText(it.stock.toString())
                etBrand.setText(it.brand)
                etCat.setText(it.category)
                tvThumbnail.text = it.thumbnail

                btnAdd.text = "Save"

                Glide.with(requireContext())
                    .load(it.thumbnail)
                    .placeholder(R.drawable.no_image_found)
                    .into(this.ivImg)

                btnAdd.setOnClickListener {
                    val product = getProduct()
                    product?.let {
                        viewModel.editProduct(navArgs.id, it)
                    }
                }
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        lifecycleScope.launch {
            viewModel.finish.collect {
                val bundle = Bundle()
                bundle.putBoolean("refresh", true)
                setFragmentResult("finish_edit_product", bundle)
                navController.popBackStack()
                navController.popBackStack()
            }
        }
    }
}