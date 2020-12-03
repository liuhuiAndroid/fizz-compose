package com.fizz.compose.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.fizz.compose.R
import com.fizz.compose.data.*
import com.fizz.compose.ui.components.FizzAppBar
import com.fizz.compose.viewmodels.MainViewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.statusBarsHeight

typealias OnMessageItemClicked = (MessageModel) -> Unit

@Composable
fun MainMessage(
    modifier: Modifier = Modifier,
    onMessageItemClicked: OnMessageItemClicked,
) {
    val viewModel: MainViewModel = viewModel()
    val suggestedDestinations by viewModel.suggestedDestinations.observeAsState()

    Surface(modifier = modifier.fillMaxSize(), color = Color.White) {
        Column {
            Spacer(Modifier.statusBarsHeight())
            FizzAppBar(
                modifier = Modifier.fillMaxWidth(),
                onNavIconPressed = { },
                title = { },
                actions = {
                    Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                        Icon(
                            asset = Icons.Outlined.MoreVert,
                            modifier = Modifier
                                .clickable(onClick = {})
                                .padding(horizontal = 12.dp, vertical = 16.dp)
                                .preferredHeight(24.dp)
                        )
                    }
                }
            )
            suggestedDestinations?.let { destinations ->
                LazyColumnFor(
                    modifier = Modifier.weight(1f).padding(start = 24.dp, end = 24.dp),
                    items = destinations
                ) { item ->
                    MessageItem(
                        modifier = Modifier.fillParentMaxWidth(),
                        item = item,
                        onMessageItemClicked = onMessageItemClicked
                    )
                    Divider()
                }
            }
        }
    }
}


@Composable
private fun MessageItem(
    modifier: Modifier = Modifier,
    item: MessageModel,
    onMessageItemClicked: OnMessageItemClicked
) {
    Row(
        modifier = modifier
            .clickable { onMessageItemClicked(item) }
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        ExploreImageContainer {
            CoilImage(
                data = item.imageUrl,
                fadeIn = true,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        Image(
                            modifier = Modifier.preferredSize(36.dp).align(Alignment.Center),
                            asset = vectorResource(id = R.drawable.ic_launcher_background)
                        )
                    }
                }
            )
        }
        Spacer(Modifier.preferredWidth(24.dp))
        Column {
            Text(
                text = item.city.nameToDisplay,
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.preferredHeight(8.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
private fun ExploreImageContainer(children: @Composable () -> Unit) {
    Surface(Modifier.preferredSize(width = 60.dp, height = 60.dp), RoundedCornerShape(4.dp)) {
        children()
    }
}
