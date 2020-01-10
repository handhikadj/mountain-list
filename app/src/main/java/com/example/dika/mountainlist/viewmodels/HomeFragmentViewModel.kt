package com.example.dika.mountainlist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.dika.mountainlist.datasource.TodoDataSource
import com.example.dika.mountainlist.datasource.TodoDataSourceFactory
import com.example.dika.mountainlist.models.Todo
import com.example.dika.mountainlist.network.ApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HomeFragmentViewModel : ViewModel() {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    private var _todoDataSourceFactory = MutableLiveData<TodoDataSource>()
    var todoPagedList: LiveData<PagedList<Todo>>
    var todoDataSource: LiveData<TodoDataSource>

    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private var _navigateToDetail = MutableLiveData<Int>()
    val navigateToDetail: LiveData<Int>
        get() = _navigateToDetail

    init {
        _status.value = ApiStatus.LOADING
        val todoDataSourceFactory = TodoDataSourceFactory(
            job, scope, _todoDataSourceFactory, _status
        )

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(10)
            .build()

        todoDataSource = todoDataSourceFactory.todoLiveDataSourceFactory

        todoPagedList = LivePagedListBuilder(todoDataSourceFactory, config).build()
    }


    fun navigateToDetail(id: Int) {
        _navigateToDetail.value = id
    }

    fun navigatedToDetail() {
        _navigateToDetail.value = null
    }
}