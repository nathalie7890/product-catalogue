package com.nathalie.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.viewModelScope
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.repository.FireStoreProductRepository
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.ui.utils.Utils.validate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(repo: ProductRepository) :
    BaseProductViewModel(repo) {

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
                error.emit("Kindly provide all information")
            }
        }
    }
}