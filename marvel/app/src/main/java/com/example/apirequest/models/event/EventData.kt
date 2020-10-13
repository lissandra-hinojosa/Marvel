package com.example.apirequest.models.event

import java.io.Serializable

data class EventData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Event>
): Serializable