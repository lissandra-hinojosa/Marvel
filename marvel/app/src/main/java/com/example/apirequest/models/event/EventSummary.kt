package com.example.apirequest.models.event

import java.io.Serializable

data class EventSummary(
    val resourceURI: String,
    val name: String
): Serializable