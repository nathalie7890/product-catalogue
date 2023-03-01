package com.nathalie.productcatalogue.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.repository.FireStoreProductRepository
import com.nathalie.productcatalogue.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: ProductRepository) :
    BaseViewModel() {
    val products: MutableLiveData<List<Product>> = MutableLiveData()
//    val refreshProducts: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onViewCreated() {
        super.onViewCreated()
        getProducts()
    }

    fun onRefresh() {
        getProducts()
    }

    //try catch block to catch error if api source is invalid
    fun getProducts() {
        viewModelScope.launch {
            val res = safeApiCall { repo.getAllProducts() }
            res?.let {
                products.value = it
            }
        }
    }


//    fun shouldRefreshProducts(refresh: Boolean) {
//        refreshProducts.value = refresh
//    }
}