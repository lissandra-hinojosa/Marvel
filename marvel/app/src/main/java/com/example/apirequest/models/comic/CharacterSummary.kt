package com.example.apirequest.models.comic

import java.io.Serializable

data class CharacterSummary(
    val resourceURI: String,
    val name: String,
    val role: String
): Serializable