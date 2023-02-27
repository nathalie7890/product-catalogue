package com.nathalie.productcatalogue.data.api

import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.model.ProductResponse
import retrofit2.http.*

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): MutableList<Product>

    @GET("/products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Product

    @POST("/products")
    suspend fun addProduct(@Body product: Product)

    @PUT("/products/{id}")
    suspend fun editProduct(@Path("id") id: Int, @Body product: Product)
}