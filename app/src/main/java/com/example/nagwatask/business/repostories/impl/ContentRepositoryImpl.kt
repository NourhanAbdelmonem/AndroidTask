package com.example.nagwatask.business.repostories.impl

import com.example.nagwatask.business.entities.Content
import com.example.nagwatask.business.repostories.abstraction.ContentRepository
import com.example.nagwatask.framework.dataSource.abstraction.ContentDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val contentDataSource: ContentDataSource) :
    ContentRepository {

    override suspend fun getContents(): Flow<List<Content>> = flow {
        emit(contentDataSource.getContents())
    }.flowOn(Dispatchers.IO)
}