package com.github.skydoves.landscapistdemo.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.android.parcel.Parcelize

@Immutable
@Parcelize
data class Poster(
        val name: String,
        val release: String,
        val playtime: String,
        val description: String,
        val poster: String
) : Parcelable
