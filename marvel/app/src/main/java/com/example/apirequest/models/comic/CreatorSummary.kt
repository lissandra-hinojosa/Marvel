package com.example.apirequest.models.comic

import java.io.Serializable

data class CreatorSummary(
    val resourceURI: String,
    val name: String,
    val role: String
): Serializable