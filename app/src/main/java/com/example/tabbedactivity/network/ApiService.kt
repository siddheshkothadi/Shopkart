package com.example.tabbedactivity.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://5e6890f8d426c00016b7e095.mockapi.io/api/sfc_kit/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("id")
    fun getPropertiesApi():
            Deferred<List<Property>>
}

object Api {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}

interface ApiService2 {
    @GET("id")
    fun getPropertiesApi2():
            Deferred<List<Property2>>
}

object Api2 {
    val retrofitService2 : ApiService2 by lazy { retrofit.create(ApiService2::class.java) }
}

interface ApiService3 {
    @GET("id")
    fun getPropertiesApi3():
            Deferred<List<Property3>>
}

object Api3 {
    val retrofitService3 : ApiService3 by lazy { retrofit.create(ApiService3::class.java) }
}

