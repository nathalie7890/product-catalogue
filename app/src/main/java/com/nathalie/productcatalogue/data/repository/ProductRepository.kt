package com.nathalie.productcatalogue.data.repository

import com.nathalie.productcatalogue.data.api.ProductApi
import com.nathalie.productcatalogue.data.model.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(id: String): Product?
    suspend fun addProduct(product: Product)
    suspend fun updateProduct(id: String, product: Product): Product?
    suspend fun deleteProduct(id: String)
}