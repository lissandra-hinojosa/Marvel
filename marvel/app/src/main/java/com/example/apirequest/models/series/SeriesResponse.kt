package com.example.apirequest.models.series

import java.io.Serializable

data class SeriesResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: SeriesData,
    val etag: String
) : Serializable