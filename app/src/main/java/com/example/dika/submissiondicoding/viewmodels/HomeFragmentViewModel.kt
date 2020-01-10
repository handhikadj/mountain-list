package com.example.dika.submissiondicoding.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dika.submissiondicoding.datasource.MountainDataSource
import com.example.dika.submissiondicoding.models.Todo
import com.example.dika.submissiondicoding.network.ApiStatus
import com.example.dika.submissiondicoding.repositories.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentViewModel : ViewModel() {
    private var _mountainListData = MutableLiveData<MountainDataSource>()
    val mountainListData: LiveData<MountainDataSource>
        get() = _mountainListData

    private var _todoListData = MutableLiveData<List<Todo>>()
    val todoListData: LiveData<List<Todo>>
        get() = _todoListData

    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private var _navigateToDetail = MutableLiveData<Int>()
    val navigateToDetail: LiveData<Int>
        get() = _navigateToDetail

    init {
        _mountainListData.value = MountainDataSource()
    }

    fun getTodo() {
        _status.value = ApiStatus.LOADING
        viewModelScope.launch {
            _todoListData.value = getTodoFromService()
            _status.value = ApiStatus.DONE
        }
    }

    private suspend fun getTodoFromService(): List<Todo> {
        return withContext(Dispatchers.IO) {
            TodoRepository().connect.getAllTodos(5)
        }
    }

    fun navigateToDetail(id: Int) {
        _navigateToDetail.value = id
    }

    fun navigatedToDetail() {
        _navigateToDetail.value = null
        _todoListData.value = null
    }
}