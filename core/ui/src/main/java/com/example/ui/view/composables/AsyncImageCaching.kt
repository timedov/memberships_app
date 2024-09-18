package com.example.ui.view.composables

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun AsyncImageCaching(
    model: Any?,
    contentDescription: String?,
    imageLoader: ImageLoader = LocalContext.current.imageLoader,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    builder: ImageRequest.Builder.() -> Unit = {}
) {
    val context = LocalContext.current
    val request = ImageRequest.Builder(context)
        .data(model)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .apply(builder)
        .build()

    AsyncImage(
        model = request,
        contentDescription = contentDescription,
        imageLoader = imageLoader,
        contentScale = contentScale,
        modifier = modifier
    )
}