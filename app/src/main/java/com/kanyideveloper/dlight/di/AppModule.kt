package com.kanyideveloper.dlight.di

import com.google.gson.Gson
import com.kanyideveloper.dlight.data.local.converters.Converters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideConverters(gson: Gson) = Converters(gson)

/*    @Provides
    @Singleton
    fun provideCoroutineDispatcher(
        coroutineDispatcher: CoroutineDispatcher
    ): CoroutineDispatcher {
        return coroutineDispatcher
    }*/

}