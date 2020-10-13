package com.example.apirequest.models.comic

import com.example.apirequest.models.character.*
import java.io.Serializable
import java.util.*

data class Comic(
    val id: Int,
    val digitalId: Int,
    val title: String,
    val issueNumber: Double,
    val variantDescription: String,
    val description: String?,
    val modified: Date,
    val isbn: String,
    val upc: String,
    val diamondCode: String,
    val ean: String,
    val issn: String,
    val format: String,
    val pageCount: Int,
    val textObjects: List<TextObject>,
    val resourceURI: String,
    val urls: List<Url>,
    val series: SeriesSummary,
    val variants: List<ComicSummary>,
    val collections: List<ComicSummary>,
    val collectedIssued: List<ComicSummary>,
    val dates: List<ComicDate>,
    val prices: List<ComicPrice>,
    val thumbnail: Thumbnail,
    val images: List<Thumbnail>,
    val creators: CreatorList,
    val characters: CharacterList,
    val stories: StoryList,
    val events: EventList
    ): Serializable