package com.assignment.android.data.api

import com.assignment.android.data.model.Images
import com.assignment.android.data.model.Albums
import com.assignment.android.utils.Resource

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getAlbums(): Resource<Albums> = safeApiCall { apiService.getAlbums() }

    override suspend fun getAlbumPictures(albumId: Int): Resource<Images> =
        safeApiCall { apiService.getAlbumPictures(albumId) }

}