package com.nathalie.productcatalogue.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nathalie.productcatalogue.data.api.ProductApi
import com.nathalie.productcatalogue.data.api.RetrofitClient
import com.nathalie.productcatalogue.data.repository.FireStoreProductRepository
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.data.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyAppDependency {
    private val logger = HttpLoggingInterceptor()
    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(logger)
        .addInterceptor(
            Interceptor {
                val builder = it.request().newBuilder()
//                builder.addHeader("X-Auth-Token", "12345")
                return@Interceptor it.proceed(builder.build())
            }
        )
        .build()


    @Provides
    @Singleton
    @Named("greeting")
    fun greeting(): String {
        return "Welcome to Dagger Hilt 2"
    }

    @Provides
    @Singleton
    @Named("welcome")
    fun welcome(): String {
        return "Welcome to Android Dev"
    }

    @Provides
    @Singleton
    fun getRetrofitClient(): ProductApi {
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return Retrofit.Builder()
            .baseUrl(RetrofitClient.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(ProductApi::class.java)
    }

    //comment this to use retrofit
//    @Provides
//    @Singleton
//    fun getProductRepository(productApi: ProductApi): ProductRepository {
//        return ProductRepositoryImpl(productApi)
//    }

    @Provides
    @Singleton
    fun getFireStore(): FirebaseFirestore {
        return Firebase.firestore
    }

    //comment this to use firebase
    @Provides
    @Singleton
    fun getFireStoreProductRepository(db: FirebaseFirestore): ProductRepository {
        return FireStoreProductRepository(db.collection("products"))
    }
}