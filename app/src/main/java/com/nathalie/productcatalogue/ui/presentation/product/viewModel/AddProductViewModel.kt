package com.nathalie.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.ui.utils.Utils.validate
import kotlinx.coroutines.launch

class AddProductViewModel(repo: ProductRepository) : BaseProductViewModel(repo) {

    fun addProduct(
        prod: Product
    ) {
        val validationStatus = validate(
            prod.title,
            prod.description,
            prod.price.toString(),
            prod.rating.toString(),
            prod.stock.toString(),
            prod.brand,
            prod.category,
            prod.discountPercentage.toString()
        )
        viewModelScope.launch {
            if (validationStatus) {
                safeApiCall { repo.addProduct(prod) }
                finish.emit(Unit)
            } else {
                viewModelScope.launch {
                    error.emit("Kindly provide all information")
                }
            }
        }
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddProductViewModel(repo) as T
        }
    }
}