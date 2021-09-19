package com.example.nagwatask.di

import com.example.nagwatask.business.repostories.abstraction.ContentRepository
import com.example.nagwatask.business.repostories.impl.ContentRepositoryImpl
import com.example.nagwatask.business.useCase.abstraction.ContentUseCase
import com.example.nagwatask.business.useCase.impl.ContentUseCaseImpl
import com.example.nagwatask.framework.dataSource.ContentApi
import com.example.nagwatask.framework.dataSource.abstraction.ContentDataSource
import com.example.nagwatask.framework.dataSource.impl.ContentDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
@Module
class ContentModule {

    @Provides
    @Singleton
    fun provideContentApi(retrofit: Retrofit): ContentApi =
        retrofit.create(ContentApi::class.java)

    @Provides
    @Singleton
    fun provideContentDataSource(contentApi: ContentApi): ContentDataSource =
        ContentDataSourceImpl(contentApi)

    @Provides
    @Singleton
    fun provideContentRepository(
        contentDataSource: ContentDataSource
    ): ContentRepository =
        ContentRepositoryImpl(contentDataSource)

    @Provides
    @Singleton
    fun providesContentUseCase(contentRepository: ContentRepository): ContentUseCase =
        ContentUseCaseImpl(contentRepository)

}