package com.example.dsolismusicapp.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.dsolismusicapp.Models.Albums
import com.example.dsolismusicapp.ui.theme.DSolisMusicAppTheme
import com.example.dsolismusicapp.ui.theme.PlayerPurpleDark
import com.example.dsolismusicapp.ui.theme.PlayerPurpleLight

@Composable
fun BarraInferior(album : Albums){
    Row (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        PlayerPurpleDark,
                        PlayerPurpleLight
                    )
                )
            )
            .padding(15.dp)
            ,
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            model = album.image,
            contentDescription = album.title,
            modifier = Modifier.size(80.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f)
        ) {
            Text(album.title,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                color = Color.White)
            Text(album.artist,
                fontSize = 14.sp,
                color = Color.White)
        }
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null,
            tint = PlayerPurpleLight,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(4.dp)
        )
    }
}

@Preview
@Composable
fun BarraInferiorView(){
    val testAlbum = Albums(
        image = "https://m.media-amazon.com/images/I/71TSFZf9zdL.jpg",
        title = "Dinasty",
        artist = "Kiss",
        description = "Disco del año 1979",
        id = "prueba01"
    )
    DSolisMusicAppTheme {
        BarraInferior(
            album = testAlbum
        )
    }
}