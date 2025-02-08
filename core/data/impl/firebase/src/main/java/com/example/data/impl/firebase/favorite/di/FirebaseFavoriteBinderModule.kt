package com.example.data.impl.firebase.favorite.di

import com.example.data.api.favorite.datasource.FavoriteDataSource
import com.example.data.impl.firebase.favorite.datasource.FirebaseFavoriteDataSource
import dagger.Binds
import dagger.Module

@Module
internal interface FirebaseFavoriteBinderModule {

   @Binds
   fun bindFavoriteDataSourceImpl(dataSource: FirebaseFavoriteDataSource): FavoriteDataSource
}