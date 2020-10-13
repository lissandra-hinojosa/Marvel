package com.example.apirequest.models.story

import com.example.apirequest.models.character.*
import com.example.apirequest.models.comic.CharacterList
import com.example.apirequest.models.comic.CreatorList
import java.io.Serializable
import java.util.*

data class Story(
    val id: Int,
    val title: String,
    val description: String,
    val resourceURI: String,
    val type: String,
    val modified: Date,
    val thumbnail: Thumbnail?,
    val comics: ComicList,
    val series: SeriesList,
    val events: EventList,
    val characters: CharacterList,
    val creators: CreatorList,
    val originalissue: ComicSummary
): Serializable