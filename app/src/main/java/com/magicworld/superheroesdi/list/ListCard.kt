package com.magicworld.superheroesdi.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.magicworld.superheroesdi.model.SuperheroeItem


@Composable
fun MyListCard(superheroe: SuperheroeItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { },
        border = BorderStroke(0.5.dp, Color.LightGray),
        shape = RoundedCornerShape(8),
        elevation = 4.dp
    ) {
        Column {
            HeaderCard(superheroe)
            BodyCard(superheroe)
        }

    }
}

@Composable
fun BodyCard(superheroe: SuperheroeItem) {
    Text(text = superheroe.powers, modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp), textAlign = TextAlign.Center)
}

@Composable
fun HeaderCard(superheroe: SuperheroeItem) {


    Row(Modifier.fillMaxWidth()) {
        AsyncImage(
            model = superheroe.urlPicture,
            contentDescription = "description of the image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(4.dp).size(100.dp).clip(CircleShape)
        )
        Text(text = superheroe.name, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), textAlign = TextAlign.Center)
    }
}
