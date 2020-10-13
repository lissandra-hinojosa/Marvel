package com.example.apirequest.models.comic

import java.io.Serializable

data class ComicResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: ComicData,
    val etag: String
): Serializable