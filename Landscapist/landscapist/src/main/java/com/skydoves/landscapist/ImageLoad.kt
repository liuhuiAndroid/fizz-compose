@file:JvmName("ImageLoad")
@file:JvmMultifileClass

package com.skydoves.landscapist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.WithConstraints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * A common image loading model for fetching an image asynchronously and
 * run composable for displaying the image.
 *
 * @param imageRequest request to execute image loading asynchronously.
 * @param executeImageRequest suspending lambda to execute an image loading request.
 * @param modifier adjust the drawing image layout or drawing decoration of the content.
 * @param content the image content to be loaded from executing for given states.
 */
@Composable
fun <T : Any> ImageLoad(
        imageRequest: T,
        executeImageRequest: suspend () -> Flow<ImageLoadState>,
        modifier: Modifier = Modifier.fillMaxWidth(),
        content: @Composable (imageState: ImageLoadState) -> Unit
) {
    var state by remember(imageRequest) { mutableStateOf<ImageLoadState>(ImageLoadState.None) }
    LaunchedEffect(imageRequest) {
        executeImageLoading(
                executeImageRequest
        ).collect {
            state = it
        }
    }
    WithConstraints(modifier) {
        content(state)
    }
}

private suspend fun executeImageLoading(executeImageRequest: suspend () -> Flow<ImageLoadState>) = flow {
    emitAll(executeImageRequest())
}.catch {
    emit(ImageLoadState.Failure(null))
}.distinctUntilChanged().flowOn(Dispatchers.IO)
