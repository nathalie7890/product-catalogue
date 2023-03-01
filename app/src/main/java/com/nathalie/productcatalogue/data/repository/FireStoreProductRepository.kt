package com.nathalie.productcatalogue.data.repository

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.nathalie.productcatalogue.data.model.Product
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreProductRepository(
    private val ref: CollectionReference
) : ProductRepository {
    override suspend fun addProduct(product: Product) {
        ref.add(product.toHashMap()).await()
    }

    override suspend fun getAllProducts(): List<Product> {
        val products = mutableListOf<Product>()
        val res = ref.get().await()
        for (document in res) {
            products.add(document.toObject(Product::class.java).copy(id = document.id))
        }

        return products
    }

    override suspend fun getProductById(id: String): Product? {
        val res = ref.document(id).get().await()
        return res.toObject(Product::class.java)?.copy(id = id)
    }

    override suspend fun deleteProduct(id: String) {
        ref.document(id).delete().await()
    }

    override suspend fun updateProduct(id: String, product: Product): Product? {
        val update = product.copy(id = id)
        ref.document(id).set(update.toHashMap()).await()
        return update
    }
}


