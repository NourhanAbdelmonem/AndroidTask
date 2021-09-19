package com.example.nagwatask.framework.dataSource.abstraction

import com.example.nagwatask.business.entities.Content

interface ContentDataSource {

    suspend fun getContents(): List<Content>

}