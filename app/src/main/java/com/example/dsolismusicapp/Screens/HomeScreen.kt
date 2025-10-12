package com.example.dsolismusicapp.Screens

import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dsolismusicapp.Components.BarraInferior
import com.example.dsolismusicapp.Components.Header
import com.example.dsolismusicapp.Components.LazyColumnAlbum
import com.example.dsolismusicapp.Components.LazyRowAlbum
import com.example.dsolismusicapp.Models.Albums
import com.example.dsolismusicapp.Services.AlbumService
import com.example.dsolismusicapp.ui.theme.DSolisMusicAppTheme
import com.example.dsolismusicapp.ui.theme.LightPastelBlue
import com.example.dsolismusicapp.ui.theme.PlayerPurpleDark
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightPastelBlue)
                .padding(horizontal = 20.dp)
        ) {
            Header(
                modifier = Modifier
                    .weight(1.1f)
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ){
                Text("Albums",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Text("See more",
                    fontSize = 18.sp,
                    color = PlayerPurpleDark
                )
            }

            LazyRow (
                modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                items(albums){ album ->
                    LazyRowAlbum(
                        album = album,
                        onClick = {}
                    )
                }
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ){
                Text("Rently Played",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Text("See more",
                    fontSize = 18.sp,
                    color = PlayerPurpleDark
                )
            }
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp)
                    .weight(2f)
            ){
                items(albums){ album ->
                    LazyColumnAlbum(
                        album = album,
                        onClick = {}
                    )
                }
            }
            BarraInferior(album = albums.random())
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