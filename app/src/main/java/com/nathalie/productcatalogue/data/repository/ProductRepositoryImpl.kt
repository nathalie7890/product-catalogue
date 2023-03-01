package com.nathalie.productcatalogue.data.repository

import com.nathalie.productcatalogue.data.api.ProductApi
import com.nathalie.productcatalogue.data.model.Product

class ProductRepositoryImpl(private val productApi: ProductApi) : ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
        return productApi.getAllProducts()
    }

    override suspend fun getProductById(id: String): Product {
        return productApi.getProductById(id)
    }

    override suspend fun addProduct(product: Product) {
        return productApi.addProduct(product)
    }

    override suspend fun updateProduct(id: String, product: Product): Product? {
        return updateProduct(id, product)
    }

    override suspend fun deleteProduct(id: String) {
        return productApi.deleteProduct(id)
    }
}