package com.example.common.di

import android.content.Context

object ComponentDepsProvider {

    inline fun <reified T : ComponentDeps> get(context: Context): T =
        (context.applicationContext as? DepsContainer)?.getDependencies(T::class.java)
            ?: throw IllegalStateException("App didn't provide dependencies")
}