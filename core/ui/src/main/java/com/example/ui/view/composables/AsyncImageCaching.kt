package com.example.ui.view.composables

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.ui.R

@Composable
fun AsyncImageCaching(
    model: Any?,
    contentDescription: String?,
    placeholder: Painter = painterResource(id = R.drawable.no_image),
    error: Painter = painterResource(id = R.drawable.no_image),
    imageLoader: ImageLoader = LocalContext.current.imageLoader,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    builder: ImageRequest.Builder.() -> Unit = {},
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
        placeholder = placeholder,
        error = error,
        contentScale = contentScale,
        modifier = modifier
    )
}