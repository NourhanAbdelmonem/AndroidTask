package com.example.nagwatask.business.repostories.abstraction

import com.example.nagwatask.business.entities.Content
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    suspend fun getContents(): Flow<List<Content>>
}