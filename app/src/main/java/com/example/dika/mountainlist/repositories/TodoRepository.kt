package com.example.dika.mountainlist.repositories

import com.example.dika.mountainlist.network.Network
import com.example.dika.mountainlist.network.services.TodoApi

class TodoRepository {
    var connect = Network.connect<TodoApi>()
}