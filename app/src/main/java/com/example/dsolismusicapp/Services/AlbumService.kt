package com.example.dsolismusicapp.Services

import com.example.dsolismusicapp.Models.Albums
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {

    @GET("albums")
    suspend fun getAllProducts() : List<Albums>

    @GET("albums/{id}")
    suspend fun getProductById(@Path("id") id: String) : Albums

}