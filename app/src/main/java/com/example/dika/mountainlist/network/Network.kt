package com.example.dika.mountainlist.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class Network {
    private val BASE_URL: String = "http://jsonplaceholder.typicode.com"

    inline fun <reified T> connect(): T = getInstance().create(T::class.java)

    fun getInstance(): Retrofit {
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .client(okHttp)
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
