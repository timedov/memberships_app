package com.example.common.di

import dagger.MapKey
import javax.inject.Scope
import kotlin.reflect.KClass

@Scope
annotation class AppScope

@Scope
annotation class FeatureScope

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ComponentDepsKey(val value: KClass<out ComponentDeps>)