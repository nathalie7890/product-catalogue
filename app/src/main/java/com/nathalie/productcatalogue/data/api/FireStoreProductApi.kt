package com.nathalie.productcatalogue.data.api

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nathalie.productcatalogue.data.model.Product

class FireStoreProductApi {
    val products = Firebase.firestore.collection("products")

    suspend fun getProducts(): ArrayList<Product> {
        val allProducts = ArrayList<Product>()
        products.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val product = Product(
                        document.id,
                        document.data["title"].toString(),
                        document.data["brand"].toString(),
                        document.data["category"].toString(),
                        document.data["description"].toString(),
                        document.data["price"].toString().toFloat(),
                        document.data["discountPercentage"].toString().toFloat(),
                        document.data["rating"].toString().toFloat(),
                        document.data["stock"].toString().toInt(),
                        ""
                    )

                    allProducts.add(product)
                    Log.d("debugging", "Repo $allProducts")
                }
            }
        return allProducts
    }
}