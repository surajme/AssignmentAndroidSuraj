package com.assignment.android.data.api

import com.assignment.android.data.model.AlbumPictures
import com.assignment.android.data.model.Albums
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("albums")
    suspend fun getAlbums(): Albums

    @GET("photos")
    suspend fun getAlbumPictures(@Query("albumId") albumId: Int): AlbumPictures
}