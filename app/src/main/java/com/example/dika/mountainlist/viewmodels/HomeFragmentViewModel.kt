package com.example.dika.mountainlist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dika.mountainlist.models.Todo
import com.example.dika.mountainlist.network.ApiStatus
import com.example.dika.mountainlist.repositories.TodoRepository
import kotlinx.coroutines.launch

class HomeFragmentViewModel : ViewModel() {
    private var _todoList = MutableLiveData<List<Todo>>()
    val todoList: LiveData<List<Todo>>
        get() = _todoList

    private var _onPage = MutableLiveData<Int>()
    val onPage: LiveData<Int>
        get() = _onPage

    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private var _showNotif = MutableLiveData<Boolean>()
    val showNotif: LiveData<Boolean>
        get() = _showNotif

    init {
        initializeData()
    }

    private fun initializeData() {
        _todoList.value = mutableListOf()
        _onPage.value = 1
        _status.value = ApiStatus.LOADING
        viewModelScope.launch {
            val todoListData = TodoRepository().connect.getAllTodos(onPage.value!!, 15)
            _status.value = ApiStatus.DONE
            val existList = _todoList.value?.toMutableList() ?: mutableListOf()
            todoListData.body()?.let {
                existList.addAll(it)
                _todoList.postValue(existList)
            }
        }
    }

    fun loadMoreData() {
        _onPage.value = onPage.value!! + 1
        viewModelScope.launch {
            val todoListData = TodoRepository().connect.getAllTodos(onPage.value!!, 15)
            val existList = _todoList.value?.toMutableList() ?: mutableListOf()
            todoListData.body()?.let {
                existList.addAll(it)
                _todoList.postValue(existList)
            }
        }
    }

    fun showNotif() {
        _showNotif.value = true
        _showNotif.value = false
    }
}
