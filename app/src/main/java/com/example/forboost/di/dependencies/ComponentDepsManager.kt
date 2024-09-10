package com.example.forboost.di.dependencies

import com.example.common.di.ComponentDeps
import javax.inject.Inject

typealias DepsMap = Map<Class<out ComponentDeps>, @JvmSuppressWildcards ComponentDeps>

class ComponentDepsManager @Inject constructor(
    private val depsMap: DepsMap
) {

    @Suppress("UNCHECKED_CAST")
    fun <T : ComponentDeps> getDependencies(key: Class<T>): T =
        (depsMap[key] as T?)
            ?: throw IllegalStateException("Missing dependencies for key $key")
}