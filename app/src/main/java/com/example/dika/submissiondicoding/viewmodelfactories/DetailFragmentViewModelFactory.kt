package com.example.dika.submissiondicoding.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dika.submissiondicoding.viewmodels.DetailFragmentViewModel

class DetailFragmentViewModelFactory(
    private val id: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailFragmentViewModel::class.java)) {
            return DetailFragmentViewModel(id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}