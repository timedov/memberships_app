package com.example.uploadpost.impl.utils

import android.content.Context
import android.net.Uri
import android.util.Base64
import java.io.File

fun String.toBase64(context: Context): String? =
    runCatching {
        when {
            startsWith("content://") || startsWith("file://") -> {
                context.contentResolver.openInputStream(Uri.parse(this))?.readBytes()
            }
            else -> File(this).takeIf { it.exists() }?.readBytes()
        }?.let { Base64.encodeToString(it, Base64.DEFAULT) }
    }.getOrNull()