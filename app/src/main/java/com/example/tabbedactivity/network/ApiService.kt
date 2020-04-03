package com.example.tabbedactivity.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//private const val BASE_URL = "http://5e6890f8d426c00016b7e095.mockapi.io/api/sfc_kit/"
private const val BASE_URL = "https://testapi.io/api/siddheshkt/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("kit_types")
    fun getKitTypesAsync():
            Deferred<List<KitType>>
    @GET("list_basic")
    fun getItems1Async():
            Deferred<List<ItemType>>
    @GET("list_advanced")
    fun getItems2Async():
            Deferred<List<ItemType>>
    @GET("list_professional")
    fun getItems3Async():
            Deferred<List<ItemType>>
}

object Api {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}