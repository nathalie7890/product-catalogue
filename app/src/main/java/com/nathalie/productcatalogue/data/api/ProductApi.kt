package com.nathalie.productcatalogue.data.api

import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): ProductResponse

    @GET("/products/{id}")
    suspend fun getProductById(
        @Path("id") searchById: Int
    ): Product
}