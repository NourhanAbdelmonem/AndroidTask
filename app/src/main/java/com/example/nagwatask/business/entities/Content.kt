package com.example.nagwatask.business.entities


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: ContentType,
    @SerializedName("url")
    val url: String
)