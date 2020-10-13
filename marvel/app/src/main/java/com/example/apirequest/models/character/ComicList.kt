package com.example.apirequest.models.character

import java.io.Serializable

data class ComicList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<ComicSummary>
): Serializable