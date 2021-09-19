package com.example.nagwatask.framework.dataSource

import com.example.nagwatask.business.entities.Content
import com.example.nagwatask.framework.utils.Constants.Companion.CONTENTS
import retrofit2.http.GET

interface ContentApi {

    @GET(CONTENTS)
    suspend fun getContents(): List<Content>
}