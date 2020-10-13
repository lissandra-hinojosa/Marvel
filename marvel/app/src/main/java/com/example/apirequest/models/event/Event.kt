package com.example.apirequest.models.event


import com.example.apirequest.models.character.*
import com.example.apirequest.models.comic.CharacterList
import com.example.apirequest.models.comic.CreatorList
import java.io.Serializable
import java.util.*

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val resourceURI: String,
    val urls: List<Url>,
    val modified: Date,
    val start: Date,
    val end: Date,
    val thumbnail: Thumbnail,
    val comics: ComicList,
    val stories: StoryList,
    val series: SeriesList,
    val characters: CharacterList,
    val creators: CreatorList,
    val next: EventSummary,
    val previous: EventSummary
    ): Serializable