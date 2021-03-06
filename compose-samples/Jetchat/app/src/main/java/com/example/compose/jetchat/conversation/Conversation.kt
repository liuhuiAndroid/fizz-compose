package com.example.compose.jetchat.conversation

import android.util.Log
import androidx.compose.foundation.ClickableText
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AmbientContentAlpha
import androidx.compose.material.AmbientContentColor
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.platform.UriHandlerAmbient
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.compose.jetchat.R
import com.example.compose.jetchat.components.JetchatAppBar
import com.example.compose.jetchat.data.exampleUiState
import com.example.compose.jetchat.theme.JetchatTheme
import com.example.compose.jetchat.theme.elevatedSurface

/**
 * Entry point for a conversation screen.
 *
 * @param uiState [ConversationUiState] that contains messages to display
 * @param navigateToProfile User action when navigation to a profile is requested
 * @param modifier [Modifier] to apply to this layout node
 * @param onNavIconPressed Sends an event up when the user clicks on the menu
 */
@Composable
fun ConversationContent(
        uiState: ConversationUiState,
        navigateToProfile: (String) -> Unit,
        modifier: Modifier = Modifier,
        onNavIconPressed: () -> Unit = { }
) {
    val authorMe = stringResource(R.string.author_me)
    val timeNow = stringResource(id = R.string.now)

    val scrollState = rememberScrollState()
    Surface(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize()) {
                Messages(
                        messages = uiState.messages,
                        navigateToProfile = navigateToProfile,
                        modifier = Modifier.weight(1f),
                        scrollState = scrollState
                )
                UserInput(
                        onMessageSent = { content ->
                            uiState.addMessage(
                                    Message(authorMe, content, timeNow)
                            )
                        },
                        scrollState
                )
            }
            // Channel name bar floats above the messages
            ChannelNameBar(
                    channelName = uiState.channelName,
                    channelMembers = uiState.channelMembers,
                    onNavIconPressed = onNavIconPressed
            )
        }
    }
}

@Composable
fun ChannelNameBar(
        channelName: String,
        channelMembers: Int,
        modifier: Modifier = Modifier,
        onNavIconPressed: () -> Unit = { }
) {
    JetchatAppBar(
            modifier = modifier,
            onNavIconPressed = onNavIconPressed,
            title = {
                Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                            text = channelName,
                            style = MaterialTheme.typography.subtitle1
                    )
                    Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                        Text(
                                text = stringResource(R.string.members, channelMembers),
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onSurface
                        )
                    }
                }
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

const val ConversationTestTag = "ConversationTestTag"

@Composable
fun Messages(
        messages: List<Message>,
        navigateToProfile: (String) -> Unit,
        scrollState: ScrollState,
        modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        ScrollableColumn(
                scrollState = scrollState,
                reverseScrollDirection = true,
                modifier = Modifier
                        .testTag(ConversationTestTag)
                        .fillMaxWidth()
        ) {
            val authorMe = stringResource(id = R.string.author_me)
            Spacer(modifier = Modifier.preferredHeight(64.dp))
            messages.forEachIndexed { index, content ->
                val prevAuthor = messages.getOrNull(index - 1)?.author
                val nextAuthor = messages.getOrNull(index + 1)?.author
                val isFirstMessageByAuthor = prevAuthor != content.author
                val isLastMessageByAuthor = nextAuthor != content.author
                if (index == 0) {
                    DayHeader("20 Aug")
                } else if (index == 4) {
                    DayHeader("Today")
                }

                Message(
                        onAuthorClick = {
                            navigateToProfile(content.author)
                        },
                        msg = content,
                        isUserMe = content.author == authorMe,
                        isFirstMessageByAuthor = isFirstMessageByAuthor,
                        isLastMessageByAuthor = isLastMessageByAuthor
                )
            }
        }
        // Jump to bottom button shows up when user scrolls past a threshold.
        val jumpThreshold = with(DensityAmbient.current) {
            JumpToBottomThreshold.toPx()
        }

        val jumpToBottomButtonEnabled = scrollState.value > jumpThreshold
        Log.i("Fizz", "scrollState.value = ${scrollState.value}")

        JumpToBottom(
                enabled = jumpToBottomButtonEnabled,
                onClicked = {
                    scrollState.smoothScrollTo(BottomScrollState)
                },
                modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun Message(
        onAuthorClick: () -> Unit,
        msg: Message,
        isUserMe: Boolean,
        isFirstMessageByAuthor: Boolean,
        isLastMessageByAuthor: Boolean
) {
    val image = if (isUserMe) {
        imageResource(id = R.drawable.ali)
    } else {
        imageResource(id = R.drawable.someone_else)
    }
    val borderColor = if (isUserMe) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.secondary
    }

    val spaceBetweenAuthors = if (isFirstMessageByAuthor) Modifier.padding(top = 8.dp) else Modifier
    Row(modifier = spaceBetweenAuthors) {
        if (isFirstMessageByAuthor) {
            Image(
                    modifier = Modifier
                            .clickable(onClick = onAuthorClick)
                            .padding(horizontal = 16.dp)
                            .preferredSize(42.dp)
                            .border(1.5.dp, borderColor, CircleShape)
                            .border(3.dp, MaterialTheme.colors.surface, CircleShape)
                            .clip(CircleShape)
                            .align(Alignment.Top),
                    asset = image,
                    contentScale = ContentScale.Crop
            )
        } else {
            Spacer(modifier = Modifier.preferredWidth(74.dp))
        }
        AuthorAndTextMessage(
                msg = msg,
                isFirstMessageByAuthor = isFirstMessageByAuthor,
                isLastMessageByAuthor = isLastMessageByAuthor,
                modifier = Modifier
                        .padding(end = 16.dp)
                        .weight(1f)
        )
    }
}

@Composable
fun AuthorAndTextMessage(
        msg: Message,
        isFirstMessageByAuthor: Boolean,
        isLastMessageByAuthor: Boolean,
        modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if (isFirstMessageByAuthor) {
            AuthorNameTimestamp(msg)
        }
        ChatItemBubble(msg, isLastMessageByAuthor)
        if (isLastMessageByAuthor) {
            Spacer(modifier = Modifier.preferredHeight(8.dp))
        } else {
            Spacer(modifier = Modifier.preferredHeight(4.dp))
        }
    }
}

@Composable
private fun AuthorNameTimestamp(msg: Message) {
    // Combine author and timestamp for a11y.
    Row(modifier = Modifier.semantics(mergeAllDescendants = true) {}) {
        Text(
                text = msg.author,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                        .alignBy(LastBaseline)
                        .paddingFrom(LastBaseline, after = 8.dp) // Space to 1st bubble
        )
        Spacer(modifier = Modifier.preferredWidth(8.dp))
        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
            Text(
                    text = msg.timestamp,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.alignBy(LastBaseline)
            )
        }
    }
}

private val ChatBubbleShape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 0.dp)
private val LastChatBubbleShape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 8.dp)

@Composable
fun DayHeader(dayString: String) {
    Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp).preferredHeight(16.dp)) {
        DayHeaderLine()
        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
            Text(
                    text = dayString,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.overline
            )
        }
        DayHeaderLine()
    }
}

@Composable
private fun RowScope.DayHeaderLine() {
    Divider(
            modifier = Modifier.weight(1f).align(Alignment.CenterVertically),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
    )
}

@Composable
fun ChatItemBubble(
        message: Message,
        lastMessageByAuthor: Boolean
) {
    val backgroundBubbleColor =
            if (MaterialTheme.colors.isLight) {
                Color(0xFFF5F5F5)
            } else {
                MaterialTheme.colors.elevatedSurface(2.dp)
            }
    val bubbleShape = if (lastMessageByAuthor) LastChatBubbleShape else ChatBubbleShape
    Column {
        Surface(color = backgroundBubbleColor, shape = bubbleShape) {
            ClickableMessage(
                    message = message
            )
        }
        message.image?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Surface(color = backgroundBubbleColor, shape = bubbleShape) {
                Image(
                        asset = imageResource(it),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.preferredSize(160.dp)
                )
            }
        }
    }
}

@Composable
fun ClickableMessage(message: Message) {
    val uriHandler = UriHandlerAmbient.current

    val styledMessage = messageFormatter(text = message.content)

    ClickableText(
            text = styledMessage,
            style = MaterialTheme.typography.body1.copy(color = AmbientContentColor.current),
            modifier = Modifier.padding(8.dp),
            onClick = {
                styledMessage
                        .getStringAnnotations(start = it, end = it)
                        .firstOrNull()
                        ?.let { annotation ->
                            when (annotation.tag) {
                                SymbolAnnotationType.LINK.name -> uriHandler.openUri(annotation.item)
                                else -> Unit
                            }
                        }
            }
    )
}

@Preview
@Composable
fun ConversationPreview() {
    JetchatTheme {
        ConversationContent(
                uiState = exampleUiState,
                navigateToProfile = { }
        )
    }
}

@Preview
@Composable
fun channelBarPrev() {
    JetchatTheme {
        ChannelNameBar(channelName = "composers", channelMembers = 52)
    }
}

@Preview
@Composable
fun DayHeaderPrev() {
    DayHeader("Aug 6")
}

private val JumpToBottomThreshold = 56.dp
private val BottomScrollState = 0f

private fun ScrollState.atBottom(): Boolean = value == BottomScrollState
