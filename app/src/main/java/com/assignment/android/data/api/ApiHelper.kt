package com.assignment.android.data.api

import com.assignment.android.data.model.Images
import com.assignment.android.data.model.Albums
import com.assignment.android.utils.Resource
import com.assignment.android.utils.ApiCall

interface ApiHelper : ApiCall {
    suspend fun getAlbums(): Resource<Albums>
    suspend fun getAlbumPictures(albumId:Int): Resource<Images>
}