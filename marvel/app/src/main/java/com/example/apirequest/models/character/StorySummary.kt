package com.example.apirequest.models.character

import java.io.Serializable

data class StorySummary(
    val resourceUri: String,
    val name: String,
    val type: String
): Serializable