package com.nathalie.productcatalogue.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductDetailViewModel(private val repo: ProductRepository) : ViewModel() {
    var product = MutableLiveData<Product>()

    fun getProductById(id: Int) {
        viewModelScope.launch {
            val res = repo.getProductById(id)
            product.value = res
        }
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductDetailViewModel(repo) as T
        }
    }
}