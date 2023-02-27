package com.nathalie.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.ui.utils.Utils
import kotlinx.coroutines.launch

class EditProductViewModel(repo: ProductRepository) : BaseProductViewModel(repo) {
    var product = MutableLiveData<Product>()
    fun getProductById(id: Int) {
        viewModelScope.launch {
            val res = safeApiCall { repo.getProductById(id) }
            res?.let {
                product.value = it
            }
        }
    }

    fun editProduct(id: Int, prod: Product) {
        val validationStatus = Utils.validate(
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
                safeApiCall { repo.editProduct(id, prod) }
                finish.emit(Unit)
            } else {
                viewModelScope.launch {
                    error.emit("Kindly provide all information")
                }
            }
        }
    }

    class Provider(private val repository: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EditProductViewModel(repository) as T
        }
    }
}