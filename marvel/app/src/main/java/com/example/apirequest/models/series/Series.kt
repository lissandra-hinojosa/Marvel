package com.example.apirequest.models.series

import com.example.apirequest.models.character.*
import com.example.apirequest.models.comic.CharacterList
import com.example.apirequest.models.comic.CreatorList
import java.io.Serializable
import java.util.*

data class Series(
    val id: Int,
    val title: String?,
    val description: String?,
    val resourceURI: String,
    val urls: List<Url>,
    val startYear: Int,
    val endYear: Int,
    val rating: String,
    val modified: Date,
    val thumbnail: Thumbnail,
    val comics: ComicList,
    val stories: StoryList,
    val events: EventList,
    val characters: CharacterList,
    val creators: CreatorList,
    val next: SeriesSummary,
    val previous: SeriesSummary
): Serializable