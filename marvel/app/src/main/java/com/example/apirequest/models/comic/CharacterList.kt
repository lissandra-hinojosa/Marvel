package com.example.apirequest.models.comic

import java.io.Serializable

data class CharacterList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<CharacterSummary>
): Serializable