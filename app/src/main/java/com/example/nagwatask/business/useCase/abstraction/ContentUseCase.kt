package com.example.nagwatask.business.useCase.abstraction

import com.example.nagwatask.business.entities.Content
import kotlinx.coroutines.flow.Flow

interface ContentUseCase {
    suspend fun getContents(): Flow<List<Content>>
}