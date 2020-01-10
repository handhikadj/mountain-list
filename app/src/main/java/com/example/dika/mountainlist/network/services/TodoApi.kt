package com.example.dika.mountainlist.network.services

import com.example.dika.mountainlist.models.Todo
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoApi {
    @GET("todos")
    suspend fun getAllTodos(@Query("_page") onPage: Int): List<Todo>
}