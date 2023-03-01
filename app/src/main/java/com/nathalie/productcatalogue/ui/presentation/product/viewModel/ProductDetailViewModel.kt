package com.nathalie.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.repository.FireStoreProductRepository
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.data.repository.ProductRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(repo: ProductRepository) :
    BaseProductViewModel(repo) {
    var product = MutableLiveData<Product>()

    fun getProductById(id: String) {
        viewModelScope.launch {
            val res = safeApiCall { repo.getProductById(id) }
            res?.let {
                product.value = it
            }
        }
    }
    fun deleteProduct(id: String) {
        viewModelScope.launch {
            safeApiCall { repo.deleteProduct(id) }
            finish.emit(Unit)
        }
    }

}