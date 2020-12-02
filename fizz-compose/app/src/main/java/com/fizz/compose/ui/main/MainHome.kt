package com.fizz.compose.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fizz.compose.ui.components.FizzAppBar
import dev.chrisbanes.accompanist.insets.statusBarsHeight

@Composable
fun MainHome(
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.statusBarsHeight())
        }
        item {
            FizzAppBar(
                title = {
                    Text(
                        text = "中原找房",
                    )
                },
                actions = {
                    Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                        Icon(
                            asset = Icons.Outlined.Search,
                            modifier = Modifier
                                .clickable(onClick = {})
                                .padding(horizontal = 12.dp, vertical = 16.dp)
                                .preferredHeight(24.dp)
                        )
                        Icon(
                            asset = Icons.Outlined.Info,
                            modifier = Modifier
                                .clickable(onClick = {})
                                .padding(horizontal = 12.dp, vertical = 16.dp)
                                .preferredHeight(24.dp)
                        )
                    }
                }
            )
        }
    }
}