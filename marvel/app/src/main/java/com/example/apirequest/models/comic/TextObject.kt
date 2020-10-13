package com.example.apirequest.models.comic

import java.io.Serializable

data class TextObject (
    val type: String,
    val language: String,
    val text: String
): Serializable