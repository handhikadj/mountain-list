package com.example.dika.submissiondicoding.network.services

import com.example.dika.submissiondicoding.models.Todo
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoApi {
    @GET("todos")
    suspend fun getAllTodos(@Query("_page") perpage: Int): List<Todo>
}