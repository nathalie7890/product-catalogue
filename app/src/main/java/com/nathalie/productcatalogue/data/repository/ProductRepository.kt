package com.nathalie.productcatalogue.data.repository

import com.nathalie.productcatalogue.data.api.ProductApi
import com.nathalie.productcatalogue.data.model.Product

class ProductRepository(private val productApi: ProductApi) {
    suspend fun getAllProducts(): List<Product> {
        return productApi.getAllProducts().products
    }

    companion object {
        var productRepositoryInstance: ProductRepository? = null

        fun getInstance(productApi: ProductApi): ProductRepository {
            if (productRepositoryInstance == null) {
                productRepositoryInstance = ProductRepository(productApi)
            }

            return productRepositoryInstance!!
        }
    }
}