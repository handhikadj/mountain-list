package com.example.dika.submissiondicoding.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dika.submissiondicoding.datasource.Mountain
import com.example.dika.submissiondicoding.datasource.MountainDataSource

class DetailFragmentViewModel(
    id: Int
) : ViewModel() {
    private var _mountainData = MutableLiveData<Mountain>()
    val mountainData: LiveData<Mountain>
        get() = _mountainData

    init {
        val showById = MountainDataSource().showById(id)
        _mountainData.value = showById
    }

}