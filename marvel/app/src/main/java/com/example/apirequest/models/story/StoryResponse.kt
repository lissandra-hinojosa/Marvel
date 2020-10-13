package com.example.apirequest.models.story

import java.io.Serializable

data class StoryResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: StoryData,
    val etag: String
): Serializable