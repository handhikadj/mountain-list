package com.example.dika.submissiondicoding.repositories

import com.example.dika.submissiondicoding.network.Network
import com.example.dika.submissiondicoding.network.services.TodoApi

class TodoRepository {
    var connect = Network.connect<TodoApi>()
}