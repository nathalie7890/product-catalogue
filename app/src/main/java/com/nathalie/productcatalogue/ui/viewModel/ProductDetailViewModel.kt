package com.nathalie.productcatalogue.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductDetailViewModel(private val repo: ProductRepository) : BaseViewModel() {
    var product = MutableLiveData<Product>()

    fun getProductById(id: Int) {
        viewModelScope.launch {
            val res = safeApiCall { repo.getProductById(id) }
            res?.let {
                product.value = it
            }
        }
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductDetailViewModel(repo) as T
        }
    }
}