package com.example.apirequest.models.character

import java.io.Serializable

data class ComicSummary(
    val resourceUri: String,
    val name: String
): Serializable