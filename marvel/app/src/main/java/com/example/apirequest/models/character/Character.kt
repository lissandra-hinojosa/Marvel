package com.example.apirequest.models.character

import java.io.Serializable
import java.util.*

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: List<Url>,
    val thumbnail: Thumbnail,
    val comics: ComicList,
    val stories: StoryList,
    val events: EventList,
    val series: SeriesList
):Serializable