package com.nathalie.productcatalogue.data.api

import com.nathalie.productcatalogue.data.model.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): ProductResponse
}