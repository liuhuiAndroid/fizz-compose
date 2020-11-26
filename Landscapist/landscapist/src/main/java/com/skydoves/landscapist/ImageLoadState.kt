package com.skydoves.landscapist

import androidx.compose.ui.graphics.ImageAsset

/** FrescoImageState is a common interface of the image requesting states. */
sealed class ImageLoadState {

    /** Request not started. */
    object None : ImageLoadState()

    /** Request is currently in progress. */
    data class Loading(val progress: Float) : ImageLoadState()

    /** Request is completed successfully amd ready to use an [ImageAsset]. */
    data class Success(val imageAsset: ImageAsset?) : ImageLoadState()

    /** Request failed. */
    data class Failure(val data: Any?) : ImageLoadState()
}
