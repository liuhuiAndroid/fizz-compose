package com.fizz.compose.ui.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.fizz.compose.data.ProfileScreenState
import com.fizz.compose.data.meProfile
import com.fizz.compose.ui.components.FizzAppBar
import com.fizz.compose.ui.theme.FizzTheme
import com.fizz.compose.R
import com.fizz.compose.ui.components.AnimatingFabContent
import com.fizz.compose.ui.components.baselineHeight
import dev.chrisbanes.accompanist.insets.statusBarsHeight

@Composable
fun MainProfile(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    val userData = meProfile

    Column(modifier = Modifier.fillMaxSize()) {
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
        WithConstraints {
            Box(modifier = Modifier.weight(1f)) {
                Surface {
                    ScrollableColumn(
                        modifier = Modifier.fillMaxSize(),
                        scrollState = scrollState
                    ) {
                        ProfileHeader(
                            scrollState,
                            userData
                        )
                        UserInfoFields(userData, maxHeight)
                    }
                }
                ProfileFab(
                    extended = scrollState.value == 0f,
                    userIsMe = userData.isMe(),
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        }
    }
}

@Composable
private fun UserInfoFields(userData: ProfileScreenState, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.preferredHeight(8.dp))

        NameAndPosition(userData)

        ProfileProperty(stringResource(R.string.display_name), userData.displayName)

        ProfileProperty(stringResource(R.string.status), userData.status)

        ProfileProperty(stringResource(R.string.twitter), userData.twitter, isLink = true)

        userData.timeZone?.let {
            ProfileProperty(stringResource(R.string.timezone), userData.timeZone)
        }

        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.preferredHeight((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun NameAndPosition(
    userData: ProfileScreenState
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Name(
            userData,
            modifier = Modifier.baselineHeight(32.dp)
        )
        Position(
            userData,
            modifier = Modifier.padding(bottom = 20.dp).baselineHeight(24.dp)
        )
    }
}

@Composable
private fun Name(userData: ProfileScreenState, modifier: Modifier = Modifier) {
    Text(
        text = userData.name,
        modifier = modifier,
        style = MaterialTheme.typography.h5
    )
}

@Composable
private fun Position(userData: ProfileScreenState, modifier: Modifier = Modifier) {
    Providers(AmbientContentAlpha provides ContentAlpha.medium) {
        Text(
            text = userData.position,
            modifier = modifier,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    data: ProfileScreenState
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(DensityAmbient.current) { offset.toDp() }

    data.photo?.let {
        val asset = imageResource(id = it)
        val ratioAsset = (asset.width / asset.height.toFloat()).coerceAtLeast(1f)

        Image(
            modifier = Modifier
                .aspectRatio(ratioAsset)
                .preferredHeightIn(max = 320.dp)
                .padding(top = offsetDp),
            asset = asset,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.baselineHeight(24.dp),
                style = MaterialTheme.typography.caption
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(
            text = value,
            modifier = Modifier.baselineHeight(24.dp),
            style = style
        )
    }
}

@Composable
fun ProfileFab(extended: Boolean, userIsMe: Boolean, modifier: Modifier = Modifier) {
    key(userIsMe) { // Prevent multiple invocations to execute during composition
        FloatingActionButton(
            onClick = { /* TODO */ },
            modifier = modifier
                .padding(16.dp)
                .preferredHeight(48.dp)
                .widthIn(min = 48.dp),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            AnimatingFabContent(
                icon = {
                    Icon(
                        asset = if (userIsMe) Icons.Outlined.Create else Icons.Outlined.Chat
                    )
                },
                text = {
                    Text(
                        text = stringResource(
                            id = if (userIsMe) R.string.edit_profile else R.string.message
                        ),
                    )
                },
                extended = extended
            )
        }
    }
}

@Preview
@Composable
fun ConvPreview480MeDefault() {
    FizzTheme {
        MainProfile()
    }
}

@Preview
@Composable
fun ProfileFabPreview() {
    FizzTheme {
        ProfileFab(extended = true, userIsMe = false)
    }
}
