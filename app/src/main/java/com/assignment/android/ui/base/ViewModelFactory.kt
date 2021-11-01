package com.assignment.android.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.android.data.api.ApiHelper
import com.assignment.android.ui.viewmodel.AlbumsViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)) {
            return AlbumsViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}