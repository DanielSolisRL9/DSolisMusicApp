package com.example.dsolismusicapp.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dsolismusicapp.Models.Albums
import com.example.dsolismusicapp.ui.theme.DSolisMusicAppTheme

@Composable
fun DetailAlbum(album: Albums, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                //.padding(16.dp)
                .shadow(6.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(10.dp)
            ) {
                Text(
                    text = "About This Album",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(album.description ?: "")
            }

        Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier
                .fillMaxWidth(.8f)
                .shadow(9.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(10.dp)
                ) {
                Text(
                    text = "Artist: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(album.artist ?: "")
            }
    }
}



@Preview
@Composable
fun DetailAlbumView(){
    val testAlbum = Albums(
        image = "https://m.media-amazon.com/images/I/71TSFZf9zdL.jpg",
        title = "Dinasty",
        artist = "Kiss",
        description = "Disco del año 1979",
        id = "prueba01"
    )
    DSolisMusicAppTheme {
        DetailAlbum(testAlbum)
    }
}