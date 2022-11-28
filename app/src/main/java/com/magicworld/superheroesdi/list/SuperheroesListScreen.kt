package com.magicworld.superheroesdi.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.magicworld.superheroesdi.R
import com.magicworld.superheroesdi.model.SuperheroeItem
import com.magicworld.superheroesdi.ui.theme.aliceAzul
import com.magicworld.superheroesdi.viewmodel.ListViewModel

@Composable
fun SuperheroesListScreen(listViewModel: ListViewModel) {
    
    Scaffold(
        topBar = {TopAppBarList("Superheroes")}
    ) {
        Box(modifier = Modifier
            .fillMaxSize()) {

            BodyList(listViewModel)
        }
    }
}

@Composable
fun TopAppBarList( title: String) {

        TopAppBar(
            title = { ListTitle( title ){  } },
            backgroundColor = aliceAzul,
            modifier = Modifier
                .padding(13.dp)
                .clip(RoundedCornerShape(100)),
            navigationIcon = {
                IconButton(onClick = { }
                ) {
                    Icon(Icons.Outlined.Menu, null, tint = Color.DarkGray)
                }
            },
            actions = {

                IconButton(onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.oscurologo),
                        contentDescription = "",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(45.dp)
                    )
                }
            }
        )

}

@Composable
fun ListTitle( title: String , onTitleClicked: () -> Unit) {
    Text(text = title , color = Color.DarkGray, fontSize = 15.sp , modifier = Modifier.clickable { onTitleClicked() })
}


@Composable
fun BodyList(listViewModel: ListViewModel) {
    listViewModel.getSuperheroesFromServer()
    val list by listViewModel.onSuperheroesLoaded.observeAsState(arrayListOf())
    LazyColumn(Modifier.fillMaxWidth()){
        items(list){ superheroe->
            ItemUser( superheroe)
        }
    }
    
}

@Composable
fun ItemUser(superheroe: SuperheroeItem) {
    MyListCard(superheroe)
}
