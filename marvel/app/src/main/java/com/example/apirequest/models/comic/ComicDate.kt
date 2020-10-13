package com.example.apirequest.models.comic

import java.io.Serializable
import java.util.*

data class ComicDate(
    val type: String,
    val date: Date
): Serializable