package com.fizz.compose.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.isFocused
import androidx.compose.ui.focusObserver
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.fizz.compose.R
import com.fizz.compose.ui.theme.FizzTheme
import com.fizz.compose.ui.theme.colorTextSecondary
import com.fizz.compose.ui.theme.colorUiFloated
import dev.chrisbanes.accompanist.insets.statusBarsHeight

@Composable
fun MainFindHouse(
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.statusBarsHeight())
            SearchBar(
                query = TextFieldValue(""),
                onQueryChange = { },
                onSearchFocusChange = { }
            )
            Divider()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalFocus::class)
@Composable
private fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    onSearchFocusChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = colorUiFloated,
        contentColor = colorTextSecondary,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .preferredHeight(56.dp)
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            if (query.text.isEmpty()) {
                SearchHint()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            ) {
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .focusObserver {
                            onSearchFocusChange(it.isFocused)
                        }
                )
                Spacer(Modifier.preferredWidth(IconSize)) // balance arrow icon
            }
        }
    }
}

private val IconSize = 48.dp

@Composable
private fun SearchHint() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize().wrapContentSize()
    ) {
        Icon(
            asset = Icons.Outlined.Search
        )
        Spacer(Modifier.preferredWidth(8.dp))
        Text(
            text = stringResource(R.string.search_house)
        )
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    FizzTheme {
        Surface {
            SearchBar(
                query = TextFieldValue(""),
                onQueryChange = { },
                onSearchFocusChange = { }
            )
        }
    }
}