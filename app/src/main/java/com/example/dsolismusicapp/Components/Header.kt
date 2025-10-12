package com.example.dsolismusicapp.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dsolismusicapp.ui.theme.DSolisMusicAppTheme
import com.example.dsolismusicapp.ui.theme.PrimaryBlue
import com.example.dsolismusicapp.ui.theme.PrimaryBlueDark

@Composable
fun Header(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            PrimaryBlueDark,
                            PrimaryBlue
                        )
                    ),
                    shape = RoundedCornerShape(30.dp)
                )
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 25.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Text(
                    text = "Good Morning!",
                    fontSize = 19.sp,
                    color = Color.White,
                    modifier = Modifier.padding(top = 20.dp)
                )
                Text(
                    text = "Daniel Solis",
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun HeaderView() {
    DSolisMusicAppTheme {
        Header()
    }
}
