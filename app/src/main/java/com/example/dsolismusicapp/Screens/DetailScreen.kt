package com.example.dsolismusicapp.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dsolismusicapp.Components.BarraInferior
import com.example.dsolismusicapp.Components.DetailAlbum
import com.example.dsolismusicapp.Components.HeaderDetail
import com.example.dsolismusicapp.Components.LazyColumDetail
import com.example.dsolismusicapp.Models.Albums
import com.example.dsolismusicapp.Services.AlbumService
import com.example.dsolismusicapp.ui.theme.DSolisMusicAppTheme
import com.example.dsolismusicapp.ui.theme.LightPastelBlue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Composable
fun DetailScreen(id : String){
    var album by remember {
        mutableStateOf<Albums?>(null)
    }

    LaunchedEffect(true) {
        try {
            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://music.juanfrausto.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(AlbumService::class.java)
            val result = withContext(Dispatchers.IO){
                service.getAlbumsById(id)
            }
            album = result
            Log.i("DetailScreen", album.toString())
        }
        catch (e : Exception){
            Log.e("DetailScreen", e.toString())
        }
    }
    album?.let { a ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightPastelBlue)
                .padding(horizontal = 20.dp)
        ) {
            HeaderDetail(album = a, modifier = Modifier.weight(1.5f))
            DetailAlbum(album = a) 
            LazyColumDetail(album = a, modifier = Modifier.weight(2f))
            BarraInferior(album = a)
        }
    }



}

//@Preview
//@Composable
//fun DetailScreenView(){
//    DSolisMusicAppTheme {
//        DetailScreen()
//    }
//}