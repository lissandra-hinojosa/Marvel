package com.example.apirequest.repositories

import com.example.apirequest.models.character.CharacterResponse
import com.example.apirequest.models.comic.ComicResponse
import com.example.apirequest.models.event.EventResponse
import com.example.apirequest.models.series.SeriesResponse
import com.example.apirequest.models.story.StoryResponse
import com.example.apirequest.services.MarvelService
import retrofit2.Call

class MarvelRepository(private val marvelService: MarvelService ) {

    fun getCharacters(offset:Int,limit:Int):Call<CharacterResponse> {
        return marvelService.getCharacters(offset,limit)
    }
    fun getComics(characterId: Int):Call<ComicResponse> {
       return marvelService.getComics(characterId)
    }
    fun getEvents(characterId: Int):Call<EventResponse> {
        return marvelService.getEvents(characterId)
    }
    fun getSeries(characterId: Int):Call<SeriesResponse> {
        return marvelService.getSeries(characterId)
    }
    fun getStories(characterId: Int):Call<StoryResponse> {
        return marvelService.getStories(characterId)
    }

}