package com.example.apirequest.models.character

import java.io.Serializable

data class CharacterData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
):Serializable