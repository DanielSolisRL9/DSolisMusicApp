package com.example.dsolismusicapp.Screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dsolismusicapp.Models.Albums
import com.example.dsolismusicapp.Services.AlbumService
import com.example.dsolismusicapp.ui.theme.DSolisMusicAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun HomeScreen(){
    var albums by remember {
        mutableStateOf(listOf<Albums>())
    }
    var loading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(true) {
        try {
            Log.i("HomeScreen", "Inicializando")
            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://music.juanfrausto.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(AlbumService::class.java)
            val result = async(Dispatchers.IO) {
                service.getAllAlbums()
            }
            Log.i("HomeScreen","${result.await()}")
            albums = result.await()
            loading = false
        }
        catch (e: Exception){
            loading = false
            Log.e("HomeScreen", e.toString())
        }

    }
    if (loading){
        Box (
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else {
        if (albums.isNotEmpty()) {
            val album = albums[0] // tomamos el primero
            Column {
                Text(text = "Artista: ${album.artist}")
                Text(text = "Título: ${album.title}")
                Text(text = "Descripción: ${album.description}")
            }
        } else {
            Text("No se encontraron álbumes")
        }
    }

}

@Preview
@Composable
fun HomeScreenView(){
    DSolisMusicAppTheme {
        HomeScreen()
    }
}