package com.example.apirequest.models.character

import java.io.Serializable

data class CharacterResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: CharacterData,
    val etag: String
):Serializable

