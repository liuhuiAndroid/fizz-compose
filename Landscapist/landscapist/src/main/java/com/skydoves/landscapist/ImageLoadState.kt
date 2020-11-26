/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
