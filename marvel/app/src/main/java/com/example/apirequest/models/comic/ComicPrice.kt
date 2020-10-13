package com.example.apirequest.models.comic

import java.io.Serializable

data class ComicPrice (
    val type: String,
    val price: Float
): Serializable