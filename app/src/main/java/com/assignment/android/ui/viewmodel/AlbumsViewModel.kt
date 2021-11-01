package com.assignment.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.android.data.api.ApiHelper
import com.assignment.android.utils.Resource
import com.assignment.android.data.model.AlbumPictures
import com.assignment.android.data.model.Albums
import kotlinx.coroutines.launch

class AlbumsViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    private val _albumsList = MutableLiveData<Albums>()
    val albumsList: LiveData<Albums>
        get() = _albumsList

    private val _albumsPictures = MutableLiveData<AlbumPictures>()
    val albumsPictures: LiveData<AlbumPictures>
        get() = _albumsPictures

    val showProgress = MutableLiveData<Boolean>()

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        showProgress.value = true
        viewModelScope.launch {
            apiHelper.getAlbums().let {
                when (it) {
                    is Resource.Success -> {
                        showProgress.postValue(false)
                        _albumsList.postValue(it.value ?: Albums())
                    }
                    is Resource.Failure -> {
                        showProgress.postValue(false)
                    }
                    Resource.Loading -> {
                        showProgress.postValue(true)
                    }
                }
            }
        }
    }

    fun fetchAlbumPictures(albumId: Int) {
        showProgress.value = true
        viewModelScope.launch {
            apiHelper.getAlbumPictures(albumId).let {
                when (it) {
                    is Resource.Success -> {
                        showProgress.value = false
                        _albumsPictures.postValue(it.value ?: AlbumPictures())
                    }
                    is Resource.Failure -> {
                        showProgress.value = false
                    }
                    Resource.Loading -> {
                        showProgress.postValue(true)
                    }
                }
            }
        }
    }
}