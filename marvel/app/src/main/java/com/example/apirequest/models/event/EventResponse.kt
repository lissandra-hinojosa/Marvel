package com.example.apirequest.models.event

import java.io.Serializable

data class EventResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: EventData,
    val etag: String
):Serializable