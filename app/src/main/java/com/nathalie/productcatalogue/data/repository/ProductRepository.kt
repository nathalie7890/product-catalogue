package com.nathalie.productcatalogue.data.repository

import com.nathalie.productcatalogue.data.api.ProductApi
import com.nathalie.productcatalogue.data.model.Product
import retrofit2.http.Path

class ProductRepository(private val productApi: ProductApi) {
    suspend fun getAllProducts(): List<Product> {
        return productApi.getAllProducts()
    }

    suspend fun getProductById(id: Int): Product {
        return productApi.getProductById(id)
    }

    suspend fun addProduct(product: Product) {
        return productApi.addProduct(product)
    }

    suspend fun editProduct(id: Int, product: Product) {
        return productApi.editProduct(id, product)
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