package com.skydoves.landscapist.coil

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageAsset
import com.skydoves.landscapist.ImageLoadState

/** CoilImageState is a state of the coil image requesting. */
sealed class CoilImageState {

    /** Request not started. */
    object None : CoilImageState()

    /** Request is currently in progress. */
    data class Loading(val progress: Float) : CoilImageState()

    /** Request is completed successfully amd ready to use an [ImageAsset]. */
    data class Success(val imageAsset: ImageAsset?) : CoilImageState()

    /** Request failed. */
    data class Failure(val errorDrawable: Drawable?) : CoilImageState()
}

/** casts an [ImageLoadState] type to a [CoilImageState]. */
@Suppress("UNCHECKED_CAST")
fun ImageLoadState.toCoilImageState(): CoilImageState {
    return when (this) {
        is ImageLoadState.None -> CoilImageState.None
        is ImageLoadState.Loading -> CoilImageState.Loading(progress)
        is ImageLoadState.Success -> CoilImageState.Success(imageAsset)
        is ImageLoadState.Failure -> CoilImageState.Failure(data as? Drawable)
    }
}
