package com.nathalie.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.repository.FireStoreProductRepository
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.data.repository.ProductRepositoryImpl
import com.nathalie.productcatalogue.ui.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(repo: ProductRepository) :
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

    fun editProduct(id: String, prod: Product) {
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
                safeApiCall { repo.updateProduct(id, prod) }
                finish.emit(Unit)
            } else {
                viewModelScope.launch {
                    error.emit("Kindly provide all information")
                }
            }
        }
    }

}