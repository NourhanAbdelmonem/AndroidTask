package com.example.nagwatask.framework.dataSource.impl

import com.example.nagwatask.business.entities.Content
import com.example.nagwatask.framework.dataSource.ContentApi
import com.example.nagwatask.framework.dataSource.abstraction.ContentDataSource
import javax.inject.Inject

class ContentDataSourceImpl @Inject constructor(private val contentApi: ContentApi) :
    ContentDataSource {

    override suspend fun getContents(): List<Content> = contentApi.getContents()
}