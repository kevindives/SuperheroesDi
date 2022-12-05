package com.magicworld.superheroesdi.list

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.magicworld.superheroesdi.R
import com.magicworld.superheroesdi.model.SuperheroeItem
import com.magicworld.superheroesdi.ui.theme.aliceAzul
import com.magicworld.superheroesdi.viewmodel.ListViewModel

@Composable
fun SuperheroesListScreen(listViewModel: ListViewModel) {
    val scrollState = rememberLazyListState()
    Scaffold(
        topBar = { TopAppBarList("Superheroes", listViewModel, scrollState) }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

            BodyList(listViewModel, scrollState)
        }
    }
}

@Composable
fun TopAppBarList(title: String, listViewModel: ListViewModel, scrollState: LazyListState) {

    val scrollUpState = listViewModel.scrollUp.observeAsState()

    listViewModel.updateScrollPosition(scrollState.firstVisibleItemIndex)

    val position by animateFloatAsState(if (scrollUpState.value == true) -150f else 0f)

    TopAppBar(
        title = { ListTitle(title) { } },
        backgroundColor = aliceAzul,
        modifier = Modifier
            .padding(13.dp)
            .clip(RoundedCornerShape(100))
            .graphicsLayer { translationY = (position) },
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
fun ListTitle(title: String, onTitleClicked: () -> Unit) {
    Text(text = title,
        color = Color.DarkGray,
        fontSize = 15.sp,
        modifier = Modifier.clickable { onTitleClicked() })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BodyList(listViewModel: ListViewModel, scrollState: LazyListState) {

    listViewModel.getSuperheroesFromServer()

    val list by listViewModel.onSuperheroesLoaded.observeAsState(arrayListOf())
    val refreshing by listViewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing, { listViewModel.refresh() })

    //other way
    /*val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = refreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = listViewModel::refresh,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.Blue,
                contentColor = Color.LightGray
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = scrollState
        ) {
            items(list) { superheroe ->
                ItemUser(superheroe)
            }
        }
    }*/
    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = scrollState
        ) {
            items(list) { superheroe ->
                ItemUser(superheroe)
            }
        }
        PullRefreshIndicator(refreshing = refreshing, state = pullRefreshState, Modifier.align(
            Alignment.TopCenter))
    }

}

@Composable
fun ItemUser(superheroe: SuperheroeItem) {
    MyListCard(superheroe)
}
