package com.nathalie.productcatalogue

import android.app.Application
import com.nathalie.productcatalogue.data.api.RetrofitClient
import com.nathalie.productcatalogue.data.repository.ProductRepository

class MyApplication : Application() {
    val productRepository = ProductRepository.getInstance(RetrofitClient.getInstance())
}