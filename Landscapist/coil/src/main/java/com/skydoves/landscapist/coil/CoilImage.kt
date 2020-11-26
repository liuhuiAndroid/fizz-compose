@file:Suppress("unused")
@file:JvmName("CoilImage")
@file:JvmMultifileClass

package com.skydoves.landscapist.coil

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.LifecycleOwnerAmbient
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LifecycleOwner
import coil.ImageLoader
import coil.request.Disposable
import coil.request.ImageRequest
import com.skydoves.landscapist.CircularRevealedImage
import com.skydoves.landscapist.DefaultCircularRevealedDuration
import com.skydoves.landscapist.ImageLoad
import com.skydoves.landscapist.ImageLoadState
import com.skydoves.landscapist.Shimmer
import com.skydoves.landscapist.ShimmerParams
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.suspendCancellableCoroutine

/**
 * Requests loading an image with a loading placeholder and error imageAsset.
 * 请求加载图像
 * ```
 * CoilImage(
 *   imageModel = imageModel,
 * shimmerParams = ShimmerParams (
 *  baseColor = backgroundColor,
 *  highlightColor = highlightColor
 * ),
 *  error = imageResource(R.drawable.error)
 * )
 * ```
 *
 * @param imageModel The data model to request image. See [ImageRequest.Builder.data] for types allowed.
 * @param context The context for creating the [ImageRequest.Builder].
 * @param lifecycleOwner The [LifecycleOwner] for constructing the [ImageRequest.Builder].
 * @param imageLoader The [ImageLoader] to use when requesting the image.
 * Defaults to [CoilAmbientProvider.getCoilImageLoader].
 * @param modifier [Modifier] used to adjust the layout or drawing content.
 * @param alignment The alignment parameter used to place the loaded [ImageAsset] in the image container.
 * @param alpha The alpha parameter used to apply for the image when it is rendered onscreen.
 * @param contentScale The scale parameter used to determine the aspect ratio scaling to be
 * used for the loaded [ImageAsset].
 * @param circularRevealedEnabled Whether to run a circular reveal animation when images are successfully loaded.
 * @param circularRevealedDuration The duration of the circular reveal animation.
 * @param colorFilter The colorFilter parameter used to apply for the image when it is rendered onscreen.
 * @param shimmerParams The shimmer related parameter used to determine constructions of the [Shimmer].
 * @param error An [ImageAsset] for showing instead of the target image when images are failed to load.
 */
@Composable
fun CoilImage(
        imageModel: Any,
        context: Context = ContextAmbient.current,
        lifecycleOwner: LifecycleOwner = LifecycleOwnerAmbient.current,
        imageLoader: ImageLoader = CoilAmbientProvider.getCoilImageLoader(),
        modifier: Modifier = Modifier.fillMaxWidth(),
        alignment: Alignment = Alignment.Center,
        alpha: Float = DefaultAlpha,
        contentScale: ContentScale = ContentScale.Crop,
        circularRevealedEnabled: Boolean = false,
        circularRevealedDuration: Int = DefaultCircularRevealedDuration,
        colorFilter: ColorFilter? = null,
        shimmerParams: ShimmerParams,
        error: ImageAsset? = null,
) {
    CoilImage(
            imageModel = imageModel,
            context = context,
            lifecycleOwner = lifecycleOwner,
            imageLoader = imageLoader,
            modifier = modifier,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
            circularRevealedEnabled = circularRevealedEnabled,
            circularRevealedDuration = circularRevealedDuration,
            shimmerParams = shimmerParams,
            failure = {
                error?.let {
                    Image(
                            asset = it,
                            alignment = alignment,
                            contentScale = contentScale,
                            colorFilter = colorFilter,
                            alpha = alpha,
                    )
                }
            }
    )
}

/**
 * Requests loading an image with a loading placeholder and error imageAsset.
 *
 * ```
 * CoilImage(
 *   imageModel = imageModel,
 *   placeHolder = imageResource(R.drawable.placeholder),
 *   error = imageResource(R.drawable.error)
 * )
 * ```
 *
 * @param imageModel The data model to request image. See [ImageRequest.Builder.data] for types allowed.
 * @param context The context for creating the [ImageRequest.Builder].
 * @param lifecycleOwner The [LifecycleOwner] for constructing the [ImageRequest.Builder].
 * @param imageLoader The [ImageLoader] to use when requesting the image.
 * Defaults to [CoilAmbientProvider.getCoilImageLoader].
 * @param modifier [Modifier] used to adjust the layout or drawing content.
 * @param alignment The alignment parameter used to place the loaded [ImageAsset] in the image container.
 * @param alpha The alpha parameter used to apply for the image when it is rendered onscreen.
 * @param contentScale The scale parameter used to determine the aspect ratio scaling to be
 * used for the loaded [ImageAsset].
 * @param circularRevealedEnabled Whether to run a circular reveal animation when images are successfully loaded.
 * @param circularRevealedDuration The duration of the circular reveal animation.
 * @param colorFilter The colorFilter parameter used to apply for the image when it is rendered onscreen.
 * @param placeHolder An [ImageAsset] to be displayed when the request is in progress.
 * @param error An [ImageAsset] for showing instead of the target image when images are failed to load.
 */
@Composable
fun CoilImage(
        imageModel: Any,
        context: Context = ContextAmbient.current,
        lifecycleOwner: LifecycleOwner = LifecycleOwnerAmbient.current,
        imageLoader: ImageLoader = CoilAmbientProvider.getCoilImageLoader(),
        modifier: Modifier = Modifier.fillMaxWidth(),
        alignment: Alignment = Alignment.Center,
        alpha: Float = DefaultAlpha,
        contentScale: ContentScale = ContentScale.Crop,
        circularRevealedEnabled: Boolean = false,
        circularRevealedDuration: Int = DefaultCircularRevealedDuration,
        colorFilter: ColorFilter? = null,
        placeHolder: ImageAsset? = null,
        error: ImageAsset? = null,
) {
    CoilImage(
            imageModel = imageModel,
            context = context,
            lifecycleOwner = lifecycleOwner,
            imageLoader = imageLoader,
            modifier = modifier,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
            circularRevealedEnabled = circularRevealedEnabled,
            circularRevealedDuration = circularRevealedDuration,
            loading = {
                placeHolder?.let {
                    Image(
                            asset = it,
                            alignment = alignment,
                            contentScale = contentScale,
                            colorFilter = colorFilter,
                            alpha = alpha
                    )
                }
            },
            failure = {
                error?.let {
                    Image(
                            asset = it,
                            alignment = alignment,
                            contentScale = contentScale,
                            colorFilter = colorFilter,
                            alpha = alpha,
                    )
                }
            }
    )
}

/**
 * Requests loading an image and create some composables based on [CoilImageState].
 *
 * ```
 * CoilImage(
 * imageModel = imageModel,
 * modifier = modifier,
 * shimmerParams = ShimmerParams (
 *  baseColor = backgroundColor,
 *  highlightColor = highlightColor
 * ),
 * failure = {
 *   Text(text = "image request failed.")
 * })
 * ```
 *
 * @param imageModel The data model to request image. See [ImageRequest.Builder.data] for types allowed.
 * @param context The context for creating the [ImageRequest.Builder].
 * @param lifecycleOwner The [LifecycleOwner] for constructing the [ImageRequest.Builder].
 * @param imageLoader The [ImageLoader] to use when requesting the image.
 * Defaults to [CoilAmbientProvider.getCoilImageLoader].
 * @param modifier [Modifier] used to adjust the layout or drawing content.
 * @param alignment The alignment parameter used to place the loaded [ImageAsset] in the image container.
 * @param alpha The alpha parameter used to apply for the image when it is rendered onscreen.
 * @param contentScale The scale parameter used to determine the aspect ratio scaling to be
 * used for the loaded [ImageAsset].
 * @param circularRevealedEnabled Whether to run a circular reveal animation when images are successfully loaded.
 * @param circularRevealedDuration The duration of the circular reveal animation.
 * @param colorFilter The colorFilter parameter used to apply for the image when it is rendered onscreen.
 * @param success Content to be displayed when the request is succeeded.
 * @param failure Content to be displayed when the request is failed.
 */
@Composable
fun CoilImage(
        imageModel: Any,
        context: Context = ContextAmbient.current,
        lifecycleOwner: LifecycleOwner = LifecycleOwnerAmbient.current,
        imageLoader: ImageLoader = CoilAmbientProvider.getCoilImageLoader(),
        modifier: Modifier = Modifier.fillMaxWidth(),
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Crop,
        alpha: Float = DefaultAlpha,
        colorFilter: ColorFilter? = null,
        circularRevealedEnabled: Boolean = false,
        circularRevealedDuration: Int = DefaultCircularRevealedDuration,
        shimmerParams: ShimmerParams,
        success: @Composable ((imageState: CoilImageState.Success) -> Unit)? = null,
        failure: @Composable ((imageState: CoilImageState.Failure) -> Unit)? = null,
) {
    CoilImage(
            imageRequest = ImageRequest.Builder(context)
                    .data(imageModel)
                    .lifecycle(lifecycleOwner)
                    .build(),
            imageLoader = imageLoader,
            modifier = modifier,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
            circularRevealedEnabled = circularRevealedEnabled,
            circularRevealedDuration = circularRevealedDuration,
            shimmerParams = shimmerParams,
            success = success,
            failure = failure
    )
}

/**
 * Requests loading an image and create some composables based on [CoilImageState].
 *
 * ```
 * CoilImage(
 * imageModel = imageModel,
 * modifier = modifier,
 * loading = {
 *   ConstraintLayout(
 *     modifier = Modifier.fillMaxSize()
 *   ) {
 *     val indicator = createRef()
 *     CircularProgressIndicator(
 *       modifier = Modifier.constrainAs(indicator) {
 *         top.linkTo(parent.top)
 *         bottom.linkTo(parent.bottom)
 *        start.linkTo(parent.start)
 *        end.linkTo(parent.end)
 *       }
 *     )
 *   }
 * },
 * failure = {
 *   Text(text = "image request failed.")
 * })
 * ```
 *
 * @param imageModel The data model to request image. See [ImageRequest.Builder.data] for types allowed.
 * @param context The context for creating the [ImageRequest.Builder].
 * @param lifecycleOwner The [LifecycleOwner] for constructing the [ImageRequest.Builder].
 * @param imageLoader The [ImageLoader] to use when requesting the image.
 * Defaults to [CoilAmbientProvider.getCoilImageLoader].
 * @param modifier [Modifier] used to adjust the layout or drawing content.
 * @param alignment The alignment parameter used to place the loaded [ImageAsset] in the image container.
 * @param alpha The alpha parameter used to apply for the image when it is rendered onscreen.
 * @param contentScale The scale parameter used to determine the aspect ratio scaling to be
 * used for the loaded [ImageAsset].
 * @param circularRevealedEnabled Whether to run a circular reveal animation when images are successfully loaded.
 * @param circularRevealedDuration The duration of the circular reveal animation.
 * @param colorFilter The colorFilter parameter used to apply for the image when it is rendered onscreen.
 * @param loading Content to be displayed when the request is in progress.
 * @param success Content to be displayed when the request is succeeded.
 * @param failure Content to be displayed when the request is failed.
 */
@Composable
fun CoilImage(
        imageModel: Any,
        context: Context = ContextAmbient.current,
        lifecycleOwner: LifecycleOwner = LifecycleOwnerAmbient.current,
        imageLoader: ImageLoader = CoilAmbientProvider.getCoilImageLoader(),
        modifier: Modifier = Modifier.fillMaxWidth(),
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Crop,
        alpha: Float = DefaultAlpha,
        colorFilter: ColorFilter? = null,
        circularRevealedEnabled: Boolean = false,
        circularRevealedDuration: Int = DefaultCircularRevealedDuration,
        loading: @Composable ((imageState: CoilImageState.Loading) -> Unit)? = null,
        success: @Composable ((imageState: CoilImageState.Success) -> Unit)? = null,
        failure: @Composable ((imageState: CoilImageState.Failure) -> Unit)? = null,
) {
    CoilImage(
            imageRequest = ImageRequest.Builder(context)
                    .data(imageModel)
                    .lifecycle(lifecycleOwner)
                    .build(),
            imageLoader = imageLoader,
            modifier = modifier,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
            circularRevealedEnabled = circularRevealedEnabled,
            circularRevealedDuration = circularRevealedDuration,
            loading = loading,
            success = success,
            failure = failure
    )
}

/**
 * Requests loading an image and create some composables based on [CoilImageState].
 *
 * ```
 * CoilImage(
 * imageRequest = ImageRequest.Builder(context)
 *      .data(imageModel)
 *      .lifecycle(lifecycleOwner)
 *      .build(),
 * modifier = modifier,
 * shimmerParams = ShimmerParams (
 *  baseColor = backgroundColor,
 *  highlightColor = highlightColor
 * ),
 * failure = {
 *   Text(text = "image request failed.")
 * })
 * ```
 *
 * @param imageRequest The request to execute.
 * @param imageLoader The [ImageLoader] to use when requesting the image.
 * Defaults to [CoilAmbientProvider.getCoilImageLoader].
 * @param modifier [Modifier] used to adjust the layout or drawing content.
 * @param alignment The alignment parameter used to place the loaded [ImageAsset] in the image container.
 * @param alpha The alpha parameter used to apply for the image when it is rendered onscreen.
 * @param contentScale The scale parameter used to determine the aspect ratio scaling to be
 * used for the loaded [ImageAsset].
 * @param circularRevealedEnabled Whether to run a circular reveal animation when images are successfully loaded.
 * @param circularRevealedDuration The duration of the circular reveal animation.
 * @param colorFilter The colorFilter parameter used to apply for the image when it is rendered onscreen.
 * @param success Content to be displayed when the request is succeeded.
 * @param failure Content to be displayed when the request is failed.
 */
@Composable
fun CoilImage(
        imageRequest: ImageRequest,
        imageLoader: ImageLoader = CoilAmbientProvider.getCoilImageLoader(),
        modifier: Modifier = Modifier.fillMaxWidth(),
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Crop,
        alpha: Float = DefaultAlpha,
        colorFilter: ColorFilter? = null,
        circularRevealedEnabled: Boolean = false,
        circularRevealedDuration: Int = DefaultCircularRevealedDuration,
        shimmerParams: ShimmerParams,
        success: @Composable ((imageState: CoilImageState.Success) -> Unit)? = null,
        failure: @Composable ((imageState: CoilImageState.Failure) -> Unit)? = null,
) {
    CoilImage(
            request = imageRequest,
            imageLoader = imageLoader,
            modifier = modifier,
    ) { imageState ->
        when (val coilImageState = imageState.toCoilImageState()) {
            is CoilImageState.None -> Unit
            is CoilImageState.Loading -> {
                Shimmer(
                        baseColor = shimmerParams.baseColor,
                        highlightColor = shimmerParams.highlightColor,
                        intensity = shimmerParams.intensity,
                        dropOff = shimmerParams.dropOff,
                        tilt = shimmerParams.tilt,
                        durationMillis = shimmerParams.durationMillis
                )
            }
            is CoilImageState.Failure -> failure?.invoke(coilImageState)
            is CoilImageState.Success -> {
                success?.invoke(coilImageState) ?: coilImageState.imageAsset?.let {
                    CircularRevealedImage(
                            asset = it,
                            alignment = alignment,
                            contentScale = contentScale,
                            alpha = alpha,
                            colorFilter = colorFilter,
                            circularRevealedEnabled = circularRevealedEnabled,
                            circularRevealedDuration = circularRevealedDuration
                    )
                }
            }
        }
    }
}

/**
 * Requests loading an image and create some composables based on [CoilImageState].
 *
 * ```
 * CoilImage(
 * imageRequest = ImageRequest.Builder(context)
 *      .data(imageModel)
 *      .lifecycle(lifecycleOwner)
 *      .build(),
 * modifier = modifier,
 * loading = {
 *   ConstraintLayout(
 *     modifier = Modifier.fillMaxSize()
 *   ) {
 *     val indicator = createRef()
 *     CircularProgressIndicator(
 *       modifier = Modifier.constrainAs(indicator) {
 *         top.linkTo(parent.top)
 *         bottom.linkTo(parent.bottom)
 *        start.linkTo(parent.start)
 *        end.linkTo(parent.end)
 *       }
 *     )
 *   }
 * },
 * failure = {
 *   Text(text = "image request failed.")
 * })
 * ```
 *
 * @param imageRequest The request to execute.
 * @param imageLoader The [ImageLoader] to use when requesting the image.
 * Defaults to [CoilAmbientProvider.getCoilImageLoader].
 * @param modifier [Modifier] used to adjust the layout or drawing content.
 * @param alignment The alignment parameter used to place the loaded [ImageAsset] in the image container.
 * @param alpha The alpha parameter used to apply for the image when it is rendered onscreen.
 * @param contentScale The scale parameter used to determine the aspect ratio scaling to be
 * used for the loaded [ImageAsset].
 * @param circularRevealedEnabled Whether to run a circular reveal animation when images are successfully loaded.
 * @param circularRevealedDuration The duration of the circular reveal animation.
 * @param colorFilter The colorFilter parameter used to apply for the image when it is rendered onscreen.
 * @param loading Content to be displayed when the request is in progress.
 * @param success Content to be displayed when the request is succeeded.
 * @param failure Content to be displayed when the request is failed.
 */
@Composable
fun CoilImage(
        imageRequest: ImageRequest,
        imageLoader: ImageLoader = CoilAmbientProvider.getCoilImageLoader(),
        modifier: Modifier = Modifier.fillMaxWidth(),
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Crop,
        alpha: Float = DefaultAlpha,
        colorFilter: ColorFilter? = null,
        circularRevealedEnabled: Boolean = false,
        circularRevealedDuration: Int = DefaultCircularRevealedDuration,
        loading: @Composable ((imageState: CoilImageState.Loading) -> Unit)? = null,
        success: @Composable ((imageState: CoilImageState.Success) -> Unit)? = null,
        failure: @Composable ((imageState: CoilImageState.Failure) -> Unit)? = null,
) {
    CoilImage(
            request = imageRequest,
            imageLoader = imageLoader,
            modifier = modifier,
    ) { imageState ->
        when (val coilImageState = imageState.toCoilImageState()) {
            is CoilImageState.None -> Unit
            is CoilImageState.Loading -> loading?.invoke(coilImageState)
            is CoilImageState.Failure -> failure?.invoke(coilImageState)
            is CoilImageState.Success -> {
                success?.invoke(coilImageState) ?: coilImageState.imageAsset?.let {
                    CircularRevealedImage(
                            asset = it,
                            alignment = alignment,
                            contentScale = contentScale,
                            alpha = alpha,
                            colorFilter = colorFilter,
                            circularRevealedEnabled = circularRevealedEnabled,
                            circularRevealedDuration = circularRevealedDuration
                    )
                }
            }
        }
    }
}

/**
 * Requests loading an image and create a composable that provides
 * the current state [ImageLoadState] of the content.
 *
 * ```
 * CoilImage(
 * imageRequest = ImageRequest.Builder(context)
 *      .data(imageModel)
 *      .lifecycle(lifecycleOwner)
 *      .build(),
 * modifier = modifier,
 * ) { imageState ->
 *   when (val coilImageState = imageState.toCoilImageState()) {
 *     is CoilImageState.None -> // do something
 *     is CoilImageState.Loading -> // do something
 *     is CoilImageState.Failure -> // do something
 *     is CoilImageState.Success ->  // do something
 *   }
 * }
 * ```
 *
 * @param request The request to execute.
 * @param imageLoader The [ImageLoader] to use when requesting the image.
 * @param modifier [Modifier] used to adjust the layout or drawing content.
 * @param content Content to be displayed for the given state.
 */
@Composable
@OptIn(ExperimentalCoroutinesApi::class)
fun CoilImage(
        request: ImageRequest,
        imageLoader: ImageLoader = CoilAmbientProvider.getCoilImageLoader(),
        modifier: Modifier = Modifier.fillMaxWidth(),
        content: @Composable (imageState: ImageLoadState) -> Unit
) {
    val context = ContextAmbient.current
    val imageLoadStateFlow = remember { MutableStateFlow<ImageLoadState>(ImageLoadState.Loading(0f)) }
    val disposable = remember { mutableStateOf<Disposable?>(null) }

    ImageLoad(
            imageRequest = request,
            executeImageRequest = {
                suspendCancellableCoroutine { cont ->
                    disposable.value = imageLoader.enqueue(
                            request.newBuilder(context).target(
                                    onSuccess = {
                                        imageLoadStateFlow.value = ImageLoadState.Success(it.toBitmap().asImageAsset())
                                    },
                                    onError = {
                                        imageLoadStateFlow.value = ImageLoadState.Failure(it?.toBitmap()?.asImageAsset())
                                    }
                            ).build()
                    )
                    cont.resume(imageLoadStateFlow) {
                        disposable.value?.dispose()
                    }
                }
            },
            modifier = modifier,
            content = content
    )
}
