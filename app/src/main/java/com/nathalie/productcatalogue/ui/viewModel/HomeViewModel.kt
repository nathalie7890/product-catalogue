package com.nathalie.productcatalogue.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: ProductRepository) : BaseViewModel() {
    val products: MutableLiveData<MutableList<Product>> = MutableLiveData()
    val refreshProducts: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onViewCreated() {
        super.onViewCreated()
        getProducts()
    }

    //try catch block to catch error if api source is invalid
    fun getProducts() {
        viewModelScope.launch {
            val res = safeApiCall { repo.getAllProducts() }
            res?.let {
                products.value = it.toMutableList()
            }
        }
    }

    fun onRefresh() {
        getProducts()
    }
    fun shouldRefreshProducts(refresh: Boolean) {
        refreshProducts.value = refresh
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }

}