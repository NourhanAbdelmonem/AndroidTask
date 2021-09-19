package com.example.nagwatask.business.useCase.impl

import com.example.nagwatask.business.entities.Content
import com.example.nagwatask.business.repostories.abstraction.ContentRepository
import com.example.nagwatask.business.useCase.abstraction.ContentUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentUseCaseImpl @Inject constructor(private val contentRepository: ContentRepository) :
    ContentUseCase {
    override suspend fun getContents(): Flow<List<Content>> = contentRepository.getContents()
}