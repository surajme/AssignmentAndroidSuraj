package com.assignment.android.data.api

import com.assignment.android.data.model.AlbumPictures
import com.assignment.android.data.model.Albums
import com.assignment.android.utils.Resource

interface ApiHelper : SafeApiCall {
    suspend fun getAlbums(): Resource<Albums>
    suspend fun getAlbumPictures(albumId:Int): Resource<AlbumPictures>
}