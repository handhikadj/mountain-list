package com.example.dika.mountainlist.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.dika.mountainlist.models.Todo
import com.example.dika.mountainlist.network.ApiStatus
import com.example.dika.mountainlist.repositories.TodoRepository
import kotlinx.coroutines.*

class TodoDataSource(
    private val job: Job,
    private val scope: CoroutineScope,
    var _status: MutableLiveData<ApiStatus>
) : PageKeyedDataSource<String, Todo>() {
    private var firstPage: Int = 1

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Todo>
    ) {
        scope.launch {
            val todoData = withContext(Dispatchers.IO) {
                TodoRepository().connect.getAllTodos(firstPage)
            }
            Log.d("todoData", "$todoData")
            _status.value = ApiStatus.DONE
            callback.onResult(todoData, null, firstPage.toString())
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Todo>) {
        firstPage++
        scope.launch {
            val todoData = withContext(Dispatchers.IO) {
                TodoRepository().connect.getAllTodos(firstPage)
            }
            callback.onResult(todoData, firstPage.toString())
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Todo>) {
        val theFirstPage = if (firstPage > 1) firstPage-- else firstPage
        scope.launch {
            val todoData = withContext(Dispatchers.IO) {
                TodoRepository().connect.getAllTodos(theFirstPage)
            }
            callback.onResult(todoData, theFirstPage.toString())
        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}
