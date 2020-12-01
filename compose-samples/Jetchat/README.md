```
val scrollState = rememberScrollState()

val backgroundColor = MaterialTheme.colors.elevatedSurface(3.dp)

```

顶部标题组件
```
JetchatAppBar(
                modifier = Modifier.fillMaxWidth(),
                onNavIconPressed = onNavIconPressed,
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
        
@Composable
fun JetchatAppBar(
        modifier: Modifier = Modifier,
        onNavIconPressed: () -> Unit = { },
        title: @Composable RowScope.() -> Unit,
        actions: @Composable RowScope.() -> Unit = {}
) {
    Column {
        val backgroundColor = MaterialTheme.colors.elevatedSurface(3.dp)
        TopAppBar(
                modifier = modifier,
                backgroundColor = backgroundColor.copy(alpha = 0.95f),
                elevation = 0.dp, // No shadow needed
                contentColor = MaterialTheme.colors.onSurface,
                actions = actions,
                title = { Row { title() } },
                navigationIcon = {
                    Image(
                            asset = vectorResource(id = R.drawable.ic_jetchat),
                            modifier = Modifier
                                    .clickable(onClick = onNavIconPressed)
                                    .padding(horizontal = 16.dp)
                    )
                }
        )
        Divider()
    }
}
```

内容组件

```
Column

ScrollableColumn

Box

Surface

Image

Text

Spacer(modifier = Modifier.preferredHeight(8.dp))

FloatingActionButton & AnimatingFabContent

AnimatingFabContent
```