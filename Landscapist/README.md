
<h1 align="center">Landscapist</h1></br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/skydoves/Landscapist/actions"><img alt="Build Status" src="https://github.com/skydoves/Frescomposable/workflows/Android%20CI/badge.svg"/></a>
  <a href="https://androidweekly.net/issues/issue-441"><img alt="Android Weekly" src="https://skydoves.github.io/badges/android-weekly.svg"/></a>
  <a href="https://github.com/skydoves"><img alt="Profile" src="https://skydoves.github.io/badges/skydoves.svg"/></a> 
</p>

<p align="center">
🍂 Jetpack Compose image loading library which can fetch and display network images using <a href="https://github.com/bumptech/glide" target="_blank"> Glide</a>, <a href="https://github.com/coil-kt/coil" target="_blank"> Coil</a>, <a href="https://github.com/facebook/fresco" target="_blank"> Fresco</a>
</p>
<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/95549795-de61c200-0a42-11eb-9ece-ac34346eb5e5.png" width="572" height="280"/>
</p>

## Usecase
You can see the use cases of this library in the below repositories.
- [DisneyCompose](https://github.com/skydoves/disneycompose) - 🧸 A demo Disney app using Jetpack Compose and Hilt based on modern Android tech-stacks and MVVM architecture.
<br>

<div class="header">
  <a href="https://github.com/bumptech/glide" target="_blank"> <img src="https://user-images.githubusercontent.com/24237865/95545537-1bc15200-0a39-11eb-883d-644f564da5d3.png" align="left" width="4%" alt="Glide" /></a>
  <h1>Glide</h1>
</div>

[![Download](https://api.bintray.com/packages/devmagician/maven/landscapist-glide/images/download.svg)](https://bintray.com/devmagician/maven/landscapist-glide/_latestVersion)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.skydoves/landscapist.svg?label=Maven%20Central)](https://search.maven.org/search?q=landscapist)<br>

<img src="https://user-images.githubusercontent.com/24237865/95661452-6abad480-0b6a-11eb-91c4-7cbe40b77927.gif" align="right" width="32%"/>

And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.github.skydoves:landscapist-glide:1.1.0"
}
```

### Usage
We can request and load images simply using a `GlideImage` composable function.
```kotlin
GlideImage(
  imageModel = imageUrl,
  // Crop, Fit, Inside, FillHeight, FillWidth, None
  contentScale = ContentScale.Crop,
  // shows an image with a circular revealed animation.
  circularRevealedEnabled = true,
  // shows a placeholder imageAsset when loading.
  placeHolder = imageResource(R.drawable.placeholder),
  // shows an error imageAsset when the request failed.
  error = imageResource(R.drawable.error)
)
```

#### RequestOptions and TransitionOptions
We can customize our request options using [RequestOptions](https://bumptech.github.io/glide/doc/options.html#requestoptions) and [TransitionOptions](https://bumptech.github.io/glide/doc/options.html#transitionoptions) for applying caching strategies, loading transformations.
```kotlin
GlideImage(
  imageModel = poster.poster,
  requestOptions = RequestOptions()
    .override(256, 256)
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .centerCrop(),
  transitionOptions = BitmapTransitionOptions.withCrossFade(), 
  contentScale = ContentScale.Crop,
  modifier = modifier,
  alignment = Alignment.Center,
)
```

#### RequestBuilder
Also we can request image by passing a [RequestBuilder](https://bumptech.github.io/glide/doc/options.html#requestbuilder). RequestBuilder is the backbone of the request in Glide and is responsible for bringing your options together with your requested url or model to start a new load.
```kotlin
GlideImage(
  imageModel = poster.poster,
  requestBuilder = Glide
    .with(ViewAmbient.current)
    .asBitmap()
    .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
    .thumbnail(0.1f)
    .transition(withCrossFade()),
  modifier = Modifier.constrainAs(image) {
    centerHorizontallyTo(parent)
    top.linkTo(parent.top)
  }.aspectRatio(0.8f)
)
```

#### GlideRequestBuilderAmbient
We can provide the same instance of the `RequestBuilder` in the composable hierarchy.
```kotlin
// customize the RequestBuilder as needed
val requestBuilder = Glide.with(ViewAmbient.current)
  .asBitmap()
  .thumbnail(0.1f)
  .transition(BitmapTransitionOptions.withCrossFade())

Providers(GlideRequestBuilderAmbient provides requestBuilder) {
  // This will automatically use the value of current RequestBuilder in the hierarchy.
  GlideImage(
    imageModel = ...
  )
}
```

<img src="https://user-images.githubusercontent.com/24237865/94174882-d6e1db00-fed0-11ea-86ec-671b5039b1b9.gif" align="right" width="32%"/>

### Composable loading, success, failure
We can create our own composable functions following requesting states.<br>
Here is an example that shows a progress indicator when loading an image,<br>
After complete requesting, the indicator will be gone and a content image will be shown.<br>
And if the request failed (e.g. network error, wrong destination), error text will be shown.
```kotlin
 GlideImage(
 imageModel = poster.poster,
 modifier = modifier,
 // shows a progress indicator when loading an image.
 loading = {
   ConstraintLayout(
     modifier = Modifier.fillMaxSize()
   ) {
     val indicator = createRef()
     CircularProgressIndicator(
       modifier = Modifier.constrainAs(indicator) {
         top.linkTo(parent.top)
         bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
       }
     )
   }
 },
 // shows an error text message when request failed.
 failure = {
   Text(text = "image request failed.")
 })
```

<div class="header">
  <a href="https://github.com/coil-kt/coil" target="_blank"> <img src="https://user-images.githubusercontent.com/24237865/95545538-1cf27f00-0a39-11eb-83dd-ef9b8c6a74cb.png" align="left" width="4%" alt="Fresco" /></a>
  <h1>Coil</h1>
</div>

[![Download](https://api.bintray.com/packages/devmagician/maven/landscapist-coil/images/download.svg) ](https://bintray.com/devmagician/maven/landscapist-coil/_latestVersion)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.skydoves/landscapist.svg?label=Maven%20Central)](https://search.maven.org/search?q=landscapist)<br>
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.github.skydoves:landscapist-coil:<version>"
}
```

### Usage
We can request and load images simply using a `CoilImage` composable function.
```kotlin
CoilImage(
  imageModel = imageUrl,
  // Crop, Fit, Inside, FillHeight, FillWidth, None
  contentScale = ContentScale.Crop,
  // shows an image with a circular revealed animation.
  circularRevealedEnabled = true,
  // shows a placeholder imageAsset when loading.
  placeHolder = imageResource(R.drawable.placeholder),
  // shows an error imageAsset when the request failed.
  error = imageResource(R.drawable.error)
)
```

#### ImageRequest and ImageLoader
We can customize request options using [ImageRequest](https://coil-kt.github.io/coil/image_requests/) and [ImageLoader](https://coil-kt.github.io/coil/image_loaders/) for providing all the necessary information for loading images like caching strategies and transformations.

```kotlin
CoilImage(
  imageRequest = ImageRequest.Builder(ContextAmbient.current)
    .data(poster.poster)
    .crossfade(true)
    .build(),
  imageLoader = ImageLoader.Builder(ContextAmbient.current)
    .availableMemoryPercentage(0.25)
    .crossfade(true)
    .build(),
  contentScale = ContentScale.Crop,
  modifier = modifier,
  alignment = Alignment.Center,
)
```

<img src="https://user-images.githubusercontent.com/24237865/94174882-d6e1db00-fed0-11ea-86ec-671b5039b1b9.gif" align="right" width="32%"/>

### Composable loading, success, failure
We can create our own composable functions following requesting states. Here is an example that shows a progress indicator when loading an image, After complete requesting, the indicator will be gone and a content image will be shown. And if the request failed (e.g. network error, wrong destination), error text will be shown.
```kotlin
CoilImage(
  imageModel = poster.poster,
  modifier = Modifier.constrainAs(image) {
    centerHorizontallyTo(parent)
    top.linkTo(parent.top)
  }.aspectRatio(0.8f),
  // shows a progress indicator when loading an image.
  loading = {
    ConstraintLayout(
      modifier = Modifier.fillMaxSize()
    ) {
      val indicator = createRef()
      CircularProgressIndicator(
        modifier = Modifier.constrainAs(indicator) {
          top.linkTo(parent.top)
          bottom.linkTo(parent.bottom)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }
      )
    }
  },
  // shows an error text message when request failed.
  failure = {
    Text(text = "image request failed.")
  })
```

<img src="https://user-images.githubusercontent.com/24237865/95812167-be3a4780-0d4f-11eb-9360-2a4a66a3fb46.gif" align="right" width="26%"/>

### Shimmer effect
We can give a shimmering effect when loading images using a `ShimmerParams`. We can also use `ShimmerParams` in `GlideImage` and `FrescoImage`.
```kotlin
 CoilImage(
 imageModel = poster.poster,
 modifier = modifier,
 // shows a shimmering effect when loading an image.
 shimmerParams = ShimmerParams(
        baseColor = MaterialTheme.colors.background,
        highlightColor = shimmerHighLight,
        durationMillis = 350,
        dropOff = 0.65f,
        tilt = 20f
      ),
 // shows an error text message when request failed.
 failure = {
   Text(text = "image request failed.")
 })
 ```

 ### CoilImageLoaderAmbient
 We can provide the same instance of the `ImageLoader` in the composable hierarchy.
 ```kotlin
 val imageLoader = ImageLoader.Builder(context)
    // customize the ImageLoader as needed
    .build()
Providers(CoilImageLoaderAmbient provides imageLoader) {
   // This will automatically use the value of current imageLoader in the hierarchy.
   CoilImage(
     imageModel = ...
   )
 }
 ```

<div class="header">
  <a href="https://github.com/facebook/fresco" target="_blank"> <img src="https://user-images.githubusercontent.com/24237865/95545540-1cf27f00-0a39-11eb-9e84-96b9df81364b.png" align="left" width="4%" alt="Fresco" /></a>
  <h1>Fresco</h1>
</div>

[![Download](https://api.bintray.com/packages/devmagician/maven/landscapist-fresco/images/download.svg) ](https://bintray.com/devmagician/maven/landscapist-fresco/_latestVersion)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.skydoves/landscapist.svg?label=Maven%20Central)](https://search.maven.org/search?q=landscapist)<br>
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.github.skydoves:landscapist-fresco:<version>"
}
```

### Initialize
We should initialize `Fresco` using [ImagePipelineConfig](https://frescolib.org/docs/configure-image-pipeline.html) in our `Application` class.<br>
If we need to fetch images from the network, recommend using `OkHttpImagePipelineConfigFactory`.<br>
By using an `ImagePipelineConfig`, we can customize caching, networking, and thread pool strategies.<br>
[Here](https://fresco.buzhidao.net/javadoc/reference/com/facebook/imagepipeline/core/ImagePipelineConfig.Builder.html) are more references related to the pipeline config.
```kotlin
class App : Application() {

  override fun onCreate() {
    super.onCreate()

    val pipelineConfig =
      OkHttpImagePipelineConfigFactory
        .newBuilder(this, OkHttpClient.Builder().build())
        .setDiskCacheEnabled(true)
        .setDownsampleEnabled(true)
        .setResizeAndRotateEnabledForNetwork(true)
        .build()

    Fresco.initialize(this, pipelineConfig)
  }
}
```

<img src="https://user-images.githubusercontent.com/24237865/95661452-6abad480-0b6a-11eb-91c4-7cbe40b77927.gif" align="right" width="32%"/>

### Usage
We can request and load images simply using a `FrescoImage` composable function.
```kotlin
FrescoImage(
  imageUrl = stringImageUrl,
  // Crop, Fit, Inside, FillHeight, FillWidth, None
  contentScale = ContentScale.Crop,
  // shows an image with a circular revealed animation.
  circularRevealedEnabled = true,
  // shows a placeholder imageAsset when loading.
  placeHolder = imageResource(R.drawable.placeholder),
  // shows an error imageAsset when the request failed.
  error = imageResource(R.drawable.error)
)
```
We can customize our requests using an [ImageRequest](https://frescolib.org/docs/image-requests.html) that consists only of a URI, we can use the helper method ImageRequest.fromURI.
```kotlin
val imageRequest = ImageRequestBuilder
  .newBuilderWithSource(uri)
  .setImageDecodeOptions(decodeOptions)
  .setLocalThumbnailPreviewsEnabled(true)
  .setLowestPermittedRequestLevel(RequestLevel.FULL_FETCH)
  .setProgressiveRenderingEnabled(false)
  .setResizeOptions(ResizeOptions(width, height))
  .build()

FrescoImage(
  imageUrl = stringImageUrl,
  imageRequest = imageRequest,
  contentScale = ContentScale.Crop)
```

<img src="https://user-images.githubusercontent.com/24237865/94174882-d6e1db00-fed0-11ea-86ec-671b5039b1b9.gif" align="right" width="32%"/>

### Composable loading, success, failure
We can create our own composable functions following requesting states.<br>
Here is an example that shows a progress indicator when loading an image,<br>
After complete requesting, the indicator will be gone and a content image will be shown.<br>
And if the request failed (e.g. network error, wrong destination), error text will be shown.
```kotlin
 FrescoImage(
 imageUrl = stringImageUrl,
 modifier = modifier,
 // shows a progress indicator when loading an image.
 loading = {
   ConstraintLayout(
     modifier = Modifier.fillMaxSize()
   ) {
     val indicator = createRef()
     CircularProgressIndicator(
       modifier = Modifier.constrainAs(indicator) {
         top.linkTo(parent.top)
         bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
       }
     )
   }
 },
 // shows an error text message when request failed.
 failure = {
   Text(text = "image request failed.")
 })
```
Also, we can customize the content image using our own composable function like below.
```kotlin
FrescoImage(
    imageUrl = imageUrl,
    // draw a resized image.
    success = { frescoImageState ->
      frescoImageState.imageAsset?.let {
        Image(
          asset = it,
          modifier = Modifier
            .width(128.dp)
            .height(128.dp))
      }
    },
    loading = { 
      // do something 
    })
```

#### FrescoImageRequestAmbient
We can provide the same instance of the `ImageRequest` in the composable hierarchy.
```kotlin
// customize the ImageRequest as needed
val imageRequest = ImageRequestBuilder
  .newBuilderWithSource(uri)
  .setImageDecodeOptions(decodeOptions)
  .setLocalThumbnailPreviewsEnabled(true)
  .setLowestPermittedRequestLevel(RequestLevel.FULL_FETCH)
  .setProgressiveRenderingEnabled(false)
  .setResizeOptions(ResizeOptions(width, height))
  .build()

Providers(FrescoImageRequestAmbient provides imageRequest) {
  // This will automatically use the value of current ImageRequest in the hierarchy.
  FrescoImage(
    imageurl = ...
  )
}
```

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/skydoves/Landscapist/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/skydoves)__ me for my next creations! 🤩

# License
```xml
Designed and developed by 2020 skydoves (Jaewoong Eum)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
