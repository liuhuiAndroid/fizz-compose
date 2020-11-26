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

package com.skydoves.landscapist.coil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.platform.ContextAmbient
import coil.ImageLoader
import coil.imageLoader

/**
 * Ambient containing the preferred [ImageLoader] for providing the same instance
 * in our composable hierarchy.
 */
val CoilImageLoaderAmbient = staticAmbientOf<ImageLoader?> { null }

/** A provider for taking the ambient instances related to the `CoilImage`. */
internal object CoilAmbientProvider {

  /** Returns the current or default [ImageLoader] for the `ColiImage` parameter. */
  @Composable
  fun getCoilImageLoader(): ImageLoader {
    return CoilImageLoaderAmbient.current ?: ContextAmbient.current.imageLoader
  }
}
