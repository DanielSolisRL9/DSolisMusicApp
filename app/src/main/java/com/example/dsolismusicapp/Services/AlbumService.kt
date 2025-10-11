package com.example.dsolismusicapp.Services

import com.example.dsolismusicapp.Models.Albums
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {

    @GET("albums")
    suspend fun getAllAlbums() : List<Albums>

    @GET("albums/{id}")
    suspend fun getAlbumsById(@Path("id") id: String) : Albums

}