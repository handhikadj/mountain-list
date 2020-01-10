package com.example.dika.submissiondicoding.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.dika.submissiondicoding.models.Todo
import com.example.dika.submissiondicoding.network.ApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job


class TodoDataSourceFactory(
    private val job: Job,
    private val scope: CoroutineScope,
    val todoLiveDataSourceFactory: MutableLiveData<TodoDataSource>,
    val apiStatus: MutableLiveData<ApiStatus>
) : DataSource.Factory<String, Todo>() {

    override fun create(): DataSource<String, Todo> {
        val todoDataSource = TodoDataSource(job, scope, apiStatus)
        todoLiveDataSourceFactory.postValue(todoDataSource)
        return todoDataSource
    }

}