package com.example.shopkart.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://siddheshkothadi.github.io/APIData/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("kit_types.json")
    fun getKitTypesAsync():
            Deferred<List<KitType>>
    @GET("list_basic.json")
    fun getItems1Async():
            Deferred<List<ItemType>>
    @GET("list_advanced.json")
    fun getItems2Async():
            Deferred<List<ItemType>>
    @GET("list_professional.json")
    fun getItems3Async():
            Deferred<List<ItemType>>
}

object Api {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}