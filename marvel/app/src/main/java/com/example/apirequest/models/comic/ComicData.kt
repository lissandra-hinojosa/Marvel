package com.example.apirequest.models.comic

import java.io.Serializable

data class ComicData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Comic>
): Serializable