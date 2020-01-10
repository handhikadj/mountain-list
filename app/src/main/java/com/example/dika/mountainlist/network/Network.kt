package com.example.dika.mountainlist.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {
    private const val BASE_URL: String = "http://jsonplaceholder.typicode.com"

    inline fun <reified T> connect(): T = getInstance().create(T::class.java)

    @JvmStatic
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(getMoshiInstance()))
            .baseUrl(BASE_URL)
            .build()
    }

    private fun getMoshiInstance(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}