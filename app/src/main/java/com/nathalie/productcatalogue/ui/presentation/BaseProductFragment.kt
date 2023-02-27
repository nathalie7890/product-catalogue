package com.nathalie.productcatalogue.ui.presentation

import com.nathalie.productcatalogue.R
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.databinding.FragmentAddProductBinding

abstract class BaseProductFragment : BaseFragment<FragmentAddProductBinding>() {
    override fun getLayoutResource() = R.layout.fragment_add_product

    fun getProduct(): Product? {
        return binding?.run {
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()
            val price = etPrice.text.toString()
            val discount = etDiscount.text.toString()
            val rating = etRating.text.toString()
            val stock = etStock.text.toString()
            val brand = etBrand.text.toString()
            val category = etCat.text.toString()

            Product(
                null,
                title,
                brand,
                category,
                desc,
                price.toFloat(),
                discount.toFloat(),
                rating.toFloat(),
                stock.toInt(),
                "",
            )
        }
    }
}