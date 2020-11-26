package com.github.skydoves.landscapistdemo.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.github.skydoves.landscapistdemo.R
import com.github.skydoves.landscapistdemo.model.MockUtil
import com.github.skydoves.landscapistdemo.model.Poster
import com.github.skydoves.landscapistdemo.theme.DisneyComposeTheme
import com.github.skydoves.landscapistdemo.theme.background800
import com.github.skydoves.landscapistdemo.theme.purple500
import com.github.skydoves.landscapistdemo.theme.shimmerHighLight
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun DisneyPosters(posters: List<Poster>, modifier: Modifier = Modifier) {
    ScrollableColumn(
            modifier = modifier
                    .background(MaterialTheme.colors.background)
    ) {
        StaggeredVerticalGrid(
                maxColumnWidth = 220.dp,
                modifier = Modifier.padding(4.dp)
        ) {
            posters.forEach { poster ->
                HomePoster(poster = poster)
            }
        }
    }
}

@Composable
fun HomePoster(poster: Poster, modifier: Modifier = Modifier) {
    Surface(
            modifier = modifier.padding(4.dp),
            color = MaterialTheme.colors.onBackground,
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(
                modifier = Modifier.clickable(
                        onClick = { },
                        indication = RippleIndication(color = purple500)
                )
        ) {
            // Create references for the composables to constrain
            val (image, title, content) = createRefs()
            CoilImage(
                    imageModel = poster.poster,
                    modifier = Modifier.constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }.aspectRatio(0.8f),
                    circularRevealedEnabled = true,
                    shimmerParams = ShimmerParams(
                            baseColor = background800,
                            highlightColor = shimmerHighLight
                    ),
                    error = imageResource(R.drawable.disney_logo)
            )
            Text(
                    text = poster.name,
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.constrainAs(title) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }.padding(8.dp)
            )
            Text(
                    text = poster.playtime,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.constrainAs(content) {
                        centerHorizontallyTo(parent)
                        top.linkTo(title.bottom)
                    }.padding(horizontal = 8.dp)
                            .padding(bottom = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun HomePosterPreviewLight() {
    DisneyComposeTheme(darkTheme = false) {
        HomePoster(
                poster = MockUtil.getMockPoster()
        )
    }
}

@Preview
@Composable
fun HomePosterPreviewDark() {
    DisneyComposeTheme(darkTheme = true) {
        HomePoster(
                poster = MockUtil.getMockPoster()
        )
    }
}
