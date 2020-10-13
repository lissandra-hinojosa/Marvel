package com.example.apirequest.models.comic

import java.io.Serializable

data class CreatorList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<CreatorSummary>
): Serializable